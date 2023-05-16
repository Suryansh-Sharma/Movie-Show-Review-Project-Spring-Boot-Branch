import Axios from "axios";
import {React, useContext, useEffect, useState} from "react";
import {toast} from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import {MovieReviewContext} from "../../context/Context";
import "./Profile.css";
import {useNavigate} from "react-router-dom";
import defaultPic from "../../icons/defaultProfile.png";

export default function Profile() {
    let navigate = useNavigate();
    const {user, baseUrl, setLogin, setUser, isLogin} =
        useContext(MovieReviewContext);
    const [loading, setLoading] = useState(true);
    const [data, setData] = useState({});
    const [file, setFile] = useState([]);
    const [modifiedData, setModifiedData] = useState({
        userFirstName: "",
        userLastName: "",
        userPassword: "",
        userEmail: "",
        userPic: "",
        oldPassword: "",
        newPassword: "",
        confirmPassword: "",
    });

    useEffect(() => {
        setLoading(true);
        Axios.get(`${baseUrl}/api/user/current-user-detail`, {
            headers: {Authorization: `Bearer  ${user.jwt_token}`},
        })
            .then((response) => {
                document.title = response.data.User.username;
                setData(response.data.User);

                // console.log(response.data.User);
            })
            .catch((error) => {
                alert("Something Went Wrong.");
                console.log(error);
            });
        setLoading(false);
    }, []);

    const updateUserPassword = () => {
        if (
            modifiedData.oldPassword.length > 0 &&
            modifiedData.newPassword.length > 0
        ) {
            if (modifiedData.newPassword === modifiedData.confirmPassword) {
                setLoading(true);

                Axios.post(
                    `${baseUrl}/api/user/change-password/${modifiedData.oldPassword}/${modifiedData.newPassword}`,
                    {},
                    {
                        headers: {Authorization: `Bearer  ${user.jwt_token}`},
                    }
                )
                    .then((response) => {
                        toast.success("🦄 Your password is upgraded sucessfully", {
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
                        toast.error(error.response.data.message, {
                            position: "top-center",
                            autoClose: 5000,
                            hideProgressBar: false,
                            closeOnClick: true,
                            pauseOnHover: true,
                            draggable: true,
                            progress: undefined,
                        });
                    });
                setLoading(false);
            } else {
                alert("New password is not matching confirm password !!");
            }
        } else {
            alert("Password is empty !!");
        }
    };
    const handleLogout = () => {
        if (isLogin) {
            const username = data.username;
            setLogin(false);
            setUser({});
            setData({});
            localStorage.removeItem("user-details");
            toast.success("Bye Bye !! , " + username, {
                position: "top-center",
                autoClose: 5000,
                hideProgressBar: false,
                closeOnClick: true,
                pauseOnHover: true,
                draggable: true,
                progress: undefined,
            });
            navigate("/");
        }
    };
    const updateProfilePic = () => {
        if (file.length === 0) {
            alert("File Input is Empty.");
        } else {
            setLoading(true);
            Axios.put(
                `${baseUrl}/api/image/update/${data.profilePic}`,
                {
                    image: file,
                },
                {
                    headers: {
                        "Content-Type": "multipart/form-data",
                        Authorization: `Bearer  ${user.jwt_token}`,
                    },
                }
            )

                .then((response) => {
                    toast.success("🦄 Your profile pic is updated sucessfully", {
                        position: "top-center",
                        autoClose: 5000,
                        hideProgressBar: false,
                        closeOnClick: true,
                        pauseOnHover: true,
                        draggable: true,
                        progress: undefined,
                    });
                    console.log(response.data);
                    setData({...data, profilePic: file.name});
                    setFile([]);
                })
                .catch((error) => {
                    alert("Something Went Wrong.");
                    console.log(error);
                });
            setLoading(false);
        }
    };
    if (loading === true) return <h1>Please wait page is loading</h1>;

    return (
        <div className='container'>
            <div className='row justify-content-center'>
                <div className='col-12 col-lg-10 col-xl-8 mx-auto'>
                    <h2 className='h3 mb-4 page-title text-info '>
                        User Profile Settings
                    </h2>
                    <div className='my-4'>
                        <ul className='nav nav-tabs mb-4' id='myTab' role='tablist'>
                            <li className='nav-item'>
                                <a
                                    className='nav-link active'
                                    id='home-tab'
                                    data-toggle='tab'
                                    href='#home'
                                    role='tab'
                                    aria-controls='home'
                                    aria-selected='false'
                                >
                                    Profile
                                </a>
                            </li>
                        </ul>
                        <form>
                            <div className='row mt-5 align-items-center'>
                                <div className='col-md-3 text-center mb-5'>
                                    <div className='avatar avatar-xl'>
                                        <img
                                            src={
                                                data.profilePic === null
                                                    ? defaultPic
                                                    : `${baseUrl}/api/image/get-by-name/${data.profilePic}`
                                            }
                                            className='avatar-img rounded-circle'
                                            alt={user.username}
                                        />
                                    </div>
                                </div>
                                <div className='col'>
                                    <div className='row align-items-center'>
                                        <div className='col-md-7'>
                                            <h4 className='mb-1'>{data.username}</h4>
                                            <p className='small mb-3'>
                                                <span className='badge badge-dark'>{data.role}</span>
                                            </p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <hr className='my-4'/>

                            <div className='form-row'>
                                <div className='form-group col-md-4'>
                                    <label>Profile Picture</label>
                                    <input
                                        type='file'
                                        accept='image/png, image/jpeg'
                                        onChange={(e) => {
                                            setFile(e.target.files[0]);
                                        }}
                                        className='form-control '
                                        placeholder='98232'
                                    />
                                </div>
                                <div className='form-group col-md-3'>
                                    <button
                                        onClick={updateProfilePic}
                                        type={"button"}
                                        className={"btn"}
                                    >
                                        Update Profile Picture.
                                    </button>
                                </div>
                            </div>
                            <hr className='my-4'/>
                            <div className='row mb-4'>
                                <div className='col-md-6'>
                                    <div className='form-group '>
                                        <label>Old Password</label>
                                        <input
                                            type='password'
                                            onChange={(event) => {
                                                setModifiedData({
                                                    ...modifiedData,
                                                    oldPassword: event.target.value,
                                                });
                                            }}
                                            className='form-control '
                                        />
                                    </div>
                                    <div className='form-group'>
                                        <label>New Password</label>
                                        <input
                                            type='password'
                                            onChange={(event) => {
                                                setModifiedData({
                                                    ...modifiedData,
                                                    newPassword: event.target.value,
                                                    userPassword: event.target.value,
                                                });
                                            }}
                                            className='form-control'
                                        />
                                    </div>
                                    <div className='form-group'>
                                        <label>Confirm Password</label>
                                        <input
                                            type='password'
                                            onChange={(event) => {
                                                setModifiedData({
                                                    ...modifiedData,
                                                    confirmPassword: event.target.value,
                                                });
                                            }}
                                            className='form-control'
                                        />
                                    </div>
                                </div>
                                <div className='col-md-6'>
                                    <p className='mb-2'>Password requirements</p>
                                    <p className='small text-muted mb-2'>
                                        To create a new password, you have to meet all of the
                                        following requirements:
                                    </p>
                                    <ul className='small text-muted pl-4 mb-0'>
                                        <li>Minimum 8 character</li>
                                        <li>At least one special character</li>
                                        <li>At least one number</li>
                                        <li>Can’t be the same as a previous password</li>
                                    </ul>
                                </div>
                            </div>
                            <button
                                type={"button"}
                                onClick={updateUserPassword}
                                className='btn btn-primary'
                            >
                                Change Password
                            </button>
                            <button
                                type={"button"}
                                onClick={handleLogout}
                                className='btn btn-primary'
                            >
                                Logout
                            </button>
                            {data.role === "ADMIN" || data.role === "MANAGER" ? (
                                <button
                                    type={"button"}
                                    // onClick={}
                                    className='btn btn-primary'
                                >
                                    Add New Movie
                                </button>
                            ) : null}
                        </form>
                    </div>
                </div>
            </div>
        </div>
    );
}
