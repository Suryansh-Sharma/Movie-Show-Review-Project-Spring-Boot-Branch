import React, { useContext, useEffect, useState } from "react";
import axios from "axios";
import { MovieReviewContext } from "../../context/Context";
import mailIcon from "../../icons/mail.png";
import "./VerificationPage.css";
function VerificationPage() {
  const {baseUrl,user,setUser} = useContext(MovieReviewContext);

  const [seconds, setSeconds] = useState(59);
  const [showResend, setShowResend] = useState(false);
  useEffect(()=>{
    sendAccountVerificationMail();
    console.log(user.jwt_token)
  },[])
  const sendAccountVerificationMail = () => {
    axios
      .post(
        `${baseUrl}/api/user/resend-verification-email/`,
        {},
        {
          headers: { Authorization: `Bearer  ${user.jwt_token}` },
        }
      )
      .then((res) => {
        console.log(res);
      })
      .catch((error) => {
        console.log(error);
      });
  };
  useEffect(() => {
    const interval = setInterval(() => {
      if (seconds > 0) {
        setSeconds(seconds - 1);
      }

      if (seconds === 0) {
        setShowResend(true);
        setSeconds(59);
      }
    }, 1000);

    return () => {
      clearInterval(interval);
    };
  }, [seconds]);
  const handleResend = () => {
    setSeconds(59);
    setShowResend(false);
    sendAccountVerificationMail();
  };
  const handleContinue = async () => {
    axios
      .get(`${baseUrl}/api/user/is-verified`, {
        headers: { Authorization: `Bearer  ${user.jwt_token}` },
      })
      .then(async (response) => {
        if (response.data !== false) {
          alert("Hello");
          const res = JSON.stringify(response.data);
          localStorage.setItem('user-details', res);
          setUser(response.data);
          console.log(response.data);
        } else {
          alert("Sorry your account is not verified !!");
        }
      })
      .catch((error) => {
        console.log(error);
      });
  };
  return (
    <div className='container'>
      {/* Top Section */}
      <div className='account-verification-top'>
        <h2 className='account-verification-title'>You've got mail</h2>
        <img src={mailIcon} alt='Mail Icon' />
      </div>

      {/* Middle Section */}
      <div className='account-verification-middle'>
        <p>We have sent a verification link to your email.</p>
        <p>Please verify your account.</p>
      </div>

      {/* Middle Section 2 */}
      <div className='account-verification-middle2'>
        <p className='avm2-text'>Didn't receive any email?</p>
        <p className='avm2-text'>or</p>
        {showResend ? (
          <p className='resend-text' onClick={handleResend}>
            Resend
          </p>
        ) : (
          <p className='resend-text'>Please wait for {seconds} seconds</p>
        )}
      </div>

      {/* Bottom Section */}
      <div className='account-verification-bottom'>
        <button className='continue-button' onClick={handleContinue}>
          Continue
        </button>
      </div>
    </div>
  );
}

export default VerificationPage;
