import React, { useContext } from "react";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import Axios from "axios";
import { toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { MovieReviewContext } from "../../context/Context";
function SignUp() {
  let Navigate = useNavigate();
  const { baseUrl, setUser, setLogin } = useContext(MovieReviewContext);
  const [data, setData] = useState({
    userName: "",
    userFirstName: "",
    userLastName: "",
    userPassword: "",
    userEmail: "",
    userPic: "",
  });
  const [confirmPassword, setConfirmPassword] = useState("");
  const [image, setImage] = useState([]);
  const submitForm = async (event) => {
    event.preventDefault();

    const { userEmail, userName, userFirstName, userLastName, userPassword } =
      data;

    if (!userEmail.includes("@") || userEmail === "") {
      return alert("Not valid email.");
    }

    if (userName.length === 0) {
      return alert("User Name is empty");
    }

    if (userFirstName.length === 0) {
      return alert("First Name is empty");
    }

    if (userLastName.length === 0) {
      return alert("Last Name is empty");
    }

    if (!image) {
      return alert("Please Upload profile pic");
    }

    if (userPassword !== confirmPassword) {
      console.log(userPassword);
      console.log(confirmPassword);
      return alert("New Password and Confirm Password is not Matching");
    }


    try {
      await Axios.post(`${baseUrl}/api/user/sign-up`, {
        email: userEmail,
        password: userPassword,
        username: userName,
      })
        .then((response) => {
          const res = JSON.stringify(response.data);
          localStorage.setItem("user-details", res);
          setUser(response.data);
          setLogin(true);
          console.log(response.data);
        })
        .catch((error) => {
          handleFilureSignUp(error.response.data.message);
        });
        
        Axios.post(
          `${baseUrl}/api/image/upload/${data.userName}`,
          {

            image:image,
          },
          {
            headers: {
              "Content-Type": "multipart/form-data",
            },
          }
        );

      handleSuccessFullSignUp();
    } catch (error) {
      console.log(error);
    }
  };

  const handleSuccessFullSignUp = () => {
    toast.success("Welcome " + data.userName, {
      position: "top-center",
      autoClose: 5000,
      hideProgressBar: false,
      closeOnClick: true,
      pauseOnHover: true,
      draggable: true,
      progress: undefined,
    });
    Navigate("/");
  };
  const handleFilureSignUp = (message) => {
    toast.error(message, {
      position: "top-center",
      autoClose: 5000,
      hideProgressBar: false,
      closeOnClick: true,
      pauseOnHover: true,
      draggable: true,
      progress: undefined,
    });
  };
  return (
    <div className='container'>
      <div className='row justify-content-center'>
        <div className='col-12 col-lg-10 col-xl-8 mx-auto'>
          <h2 className='h3 mb-4 page-title text-info '>
            New User Registration.
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
              <hr className='my-4' />
              <div className='form-row'>
                <div className='form-group col-md-4'>
                  <label>Firstname</label>
                  <input
                    type='text'
                    onChange={(event) => {
                      setData({ ...data, userFirstName: event.target.value });
                    }}
                    className='form-control'
                    placeholder='Suryansh'
                  />
                </div>
                <div className='form-group col-md-4'>
                  <label>Lastname</label>
                  <input
                    type='text'
                    onChange={(event) => {
                      setData({ ...data, userLastName: event.target.value });
                    }}
                    className='form-control'
                    placeholder='Sharma'
                  />
                </div>
                <div className='form-group col-md-4'>
                  <label>User Name</label>
                  <input
                    type='text'
                    onChange={(event) => {
                      setData({ ...data, userName: event.target.value });
                    }}
                    className='form-control'
                    placeholder='Suryansh@1234'
                  />
                </div>
              </div>
              <div className='form-group'>
                <label>Email</label>
                <input
                  type='email'
                  onChange={(event) => {
                    setData({ ...data, userEmail: event.target.value });
                  }}
                  className='form-control'
                  id='inputEmail4'
                  placeholder='suryansh@gmail.com'
                />
              </div>
              <div className='form-row'>
                <div className='form-group col-md-4'>
                  <label>Profile Pic</label>
                  <input
                    type='file'
                    accept='image/png, image/jpeg'
                    onChange={(e) => {
                      setImage(e.target.files[0]);
                    }}
                    className='form-control'
                  />
                </div>
              </div>
              <hr className='my-4' />
              <div className='row mb-4'>
                <div className='col-md-6'>
                  <div className='form-group'>
                    <label>New Password</label>
                    <input
                      type='password'
                      onChange={(event) => {
                        setData({ ...data, userPassword: event.target.value });
                      }}
                      className='form-control'
                    />
                  </div>
                  <div className='form-group'>
                    <label>Confirm Password</label>
                    <input
                      type='password'
                      onChange={(event) => {
                        setConfirmPassword(event.target.value);
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
                    {/* <li>Minimum 8 character</li> */}
                    <li>At least one special character</li>
                    <li>At least one number</li>
                    <li>Can’t be the same as a previous password</li>
                  </ul>
                </div>
              </div>
              <button type='submit' onClick={submitForm} className='btn'>
                Save Change
              </button>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
}

export default SignUp;
