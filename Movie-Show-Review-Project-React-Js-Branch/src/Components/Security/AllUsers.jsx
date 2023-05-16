import React, {useContext, useEffect, useState} from "react";
import "./AllUsers.css";
import {MovieReviewContext} from "../../context/Context";
import axios from "axios";
import {toast} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

function AllUsers() {
    const {baseUrl, user} = useContext(MovieReviewContext);
    const [data, setData] = useState({});
    const [isLoading, setLoading] = useState(true)
    useEffect(() => {
        document.title = "App Role Manager"
        handleFetchData();
    }, [])
    const handleFetchData = () => {
        axios
            .get(`${baseUrl}/api/user/all-users-with-role`,
                {
                    headers: {Authorization: `Bearer  ${user.jwt_token}`},
                }
            )
            .then(res => {
                console.log(res.data);
                setData(res.data);
                setLoading(false)
            })
            .catch((error) => {
                console.log(error);
            });
    };
    const handleChangeRole = (userId) => {
        axios
            .post(`${baseUrl}/api/user/change-user-role/${userId}`, {}, {
                headers: {Authorization: `Bearer  ${user.jwt_token}`},
            })
            .then(res => {
                toast.success(res.data, {
                    position: "top-center",
                    autoClose: 5000,
                    hideProgressBar: false,
                    closeOnClick: true,
                    pauseOnHover: true,
                    draggable: true,
                    progress: undefined,
                });
            })
            .catch((error) => {
                if (error.response.data.message !== null) {
                    toast.error(error.response.data.message, {
                        position: "top-center",
                        autoClose: 5000,
                        hideProgressBar: false,
                        closeOnClick: true,
                        pauseOnHover: true,
                        draggable: true,
                        progress: undefined,
                    });
                }
                console.log(error);
            });
    };
    if (isLoading) return <h1>Please wait page is loading !!</h1>
    return (
        <div className={"All-Users-Page"}>
            <div className={"All-Users-Page-title"}>
                <h4>Change User Roles Here</h4>
            </div>
            <div className={"All-Users-Page-Header"}>
                <span>Total Users :- {data.total_users}</span>
                <span>Total Managers :- {data.total_managers}</span>
            </div>
            <div className={"All-Users-Data"}>
                {data.users.map((user) => (
                    <div className='user-card' key={user.user_id}>
                        <div className='user-card__avatar'>
                            <img
                                src={`${baseUrl}/api/image/get-by-name/${user.profile_pic}`}
                                alt={user.username}
                                className={"user-card__avatar__image"}
                            />
                        </div>
                        <div className='user-card__details'>
                            <span className='user-card__username'>{user.username}</span>
                            <span className='user-card__role'> - {user.role}</span>{" "}
                            {/* add a separator before role */}
                        </div>
                        <div
                            className={"user-card__last"}
                            onClick={() => handleChangeRole(user.user_id)}
                        >
                            <span>Assign role of</span>
                            <span>_</span>
                            <span>{user.role === "USER" ? "MANAGER" : "USER"}</span>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
}

export default AllUsers;
