import {React, useContext} from "react";
import {useNavigate} from "react-router-dom";
import {MovieReviewContext} from "../../context/Context";
import titleLogo from "../../icons/movie.png";
import "./Navbar.css";

const Navigation = () => {
    const {isLogin, user} = useContext(MovieReviewContext);
    let navigate = useNavigate();

    const handleLogin = () => {
        // If a User is Already Login then Logout.
        if (isLogin) {
            navigate("/Profile");
        } else {
            navigate("/login");
        }
    };

    return (
        <div className='Navbar' id='Navbar'>
            <nav className='navbar navbar-expand-lg navbar-default'>
                {/* <a className="navbar-brand" onClick={()=>{navigate("/")}}>Movie & Shows</a> */}
                <img
                    src={titleLogo}
                    className={"navbar-brand"}
                    onClick={() => {
                        navigate("/");
                    }}
                ></img>
                <button
                    className='navbar-toggler'
                    type='button'
                    data-toggle='collapse'
                    data-target='#navbarSupportedContent'
                    aria-controls='navbarSupportedContent'
                    aria-expanded='false'
                    aria-label='Toggle navigation'
                >
                    <span className='navbar-toggler-icon'></span>
                </button>

                <div className='collapse navbar-collapse' id='navbarSupportedContent'>
                    <ul className='navbar-nav mr-auto'>
                        <li className='nav-item'>
                            <a
                                className='nav-link'
                                onClick={() => {
                                    navigate("/movies/" + "most_voted/" + "Most Voted");
                                }}
                            >
                                Most Voted Movies{" "}
                            </a>
                        </li>
                    </ul>
                    <ul className='navbar-nav ml-auto'>
                        <li className='nav-item'>
                            <a className='nav-link' onClick={() => navigate("/")}>
                                Home Page
                            </a>
                        </li>
                        <li className='nav-item'>
                            <a
                                className='nav-link'
                                onClick={() => {
                                    navigate("/categories");
                                }}
                            >
                                Categories
                            </a>
                        </li>
                        {
                            isLogin && user.User.role === "ADMIN" ?
                                <li className='nav-item'>
                                    <a
                                        className='nav-link'
                                        onClick={() =>
                                            navigate(
                                                "all-users"
                                            )
                                        }
                                    >
                                        All Users
                                    </a>
                                </li>
                                : null
                        }

                        {isLogin ? (
                            <li className='nav-item profile'>
                                <a
                                    className='nav-link'
                                    onClick={() => navigate("/profile/" + isLogin.userName)}
                                >
                                    {isLogin.userName}
                                </a>
                            </li>
                        ) : null}
                        <button className={"btn"} onClick={handleLogin}>
                            {isLogin ? (
                                <div>{user.User.username}</div>
                            ) : (
                                <div>Login/SignUp</div>
                            )}
                        </button>
                    </ul>
                </div>
            </nav>
        </div>
    );
};

export default Navigation;
