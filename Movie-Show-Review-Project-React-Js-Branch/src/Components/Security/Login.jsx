import Axios from "axios";
import React, { useContext, useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { MovieReviewContext } from '../../context/Context';
import "./Login.css";
function Login() {
    const {baseUrl,setUser,setLogin} = useContext(MovieReviewContext);
    useEffect(() => {
        window.scroll(0, 0);
      }, []);
    let Navigate = useNavigate();
    const [data,setData]=useState({
        username:"",
        password:""
    });
    const [error,setError]=useState({
        username:"",
        password:""
    });
    const handleToast=(value)=>{
        if(!value){
            toast.error('😕 Invalid User Name and Password.', {
                position: "top-center",
                autoClose: 5000,
                hideProgressBar: false,
                closeOnClick: true,
                pauseOnHover: true,
                draggable: true,
                progress: undefined,
            });
        }else{
            toast.success('Welcome '+ data.username,{
                position: "top-center",
                autoClose: 5000,
                hideProgressBar: false,
                closeOnClick: true,
                pauseOnHover: true,
                draggable: true,
                progress: undefined,
                });       
            Navigate("/")
        }
    }
    const submitForm=()=>{
        if(data.username.length===0){
            alert("Not valid username.");
            setError({...error,email:"Enter Valid User Name."});
        }
        else if(data.password.length===0){
            alert("No Valid Password.");
            setError({...error,password:"Enter Valid Password."});
        }
        else{
            console.log(data);
            Axios.post(`${baseUrl}/api/user/account-login`,data)
            .then((response)=>{
                const res = JSON.stringify(response.data);
                localStorage.setItem('user-details', res);
                setUser(response.data);
                setLogin(true);
                handleToast(true);
                console.log(response.data);
            })
            .catch((error)=>{
                handleToast(false);
            })
        }
        
    }
    const handleForgotPassword=()=>{
        if(data.username.length<=0){
            alert("Please Enter Username !!");
            return;
        }
        Axios.post(`${baseUrl}/api/user/send-forgot-password-mail/${data.username}`)
        .then(res=>{
            toast.success("We have send an email for forgot password",{
                position: "top-center",
                autoClose: 5000,
                hideProgressBar: false,
                closeOnClick: true,
                pauseOnHover: true,
                draggable: true,
                progress: undefined,
                });   
        })
        .catch(error=>{
            console.log(error);
        });
    }
    return (
        <div className={"loginPage"}>
            <h1 className={"pageTitle"}>Welcome To Login Page.</h1>
            
            <div className={"loginForm"} >
                <div className="mb-3 emailLogin">
                    <label className="form-label">User Name</label>
                    <input type="text" className="form-control" onChange={(event)=>{
                        setData({...data,username:event.target.value});
                    }} aria-describedby="emailHelp" />
                    <div id="emailHelp" className="formError">{error.username}</div>
                </div>
                <div className="mb-3 passwordLogin">
                    <label 
                     className="form-label ">Password</label>
                    <input type="password" className="form-control" onChange={(event)=>{
                    setData({...data,password:event.target.value});
                    }}/>
                    <div id="passwordHelp" className="formError">{error.password}</div>
                
                </div>
                <div className="mb-3 form-check redicetUrl">
                    <span onClick={handleForgotPassword}>
                        <span>Forgot Password</span>
                    </span>
                </div>
                <div className="mb-3 form-check redicetUrl">
                    <span onClick={()=>{ Navigate("/signUp")}}>
                        <span>New User/SignUp.</span>
                    </span>
                </div>
                <button type="button" className="btn btn-primary buttonLogin"
                    onClick={submitForm}
                >Submit</button>
            </div>
        </div>
    )
}

export default Login