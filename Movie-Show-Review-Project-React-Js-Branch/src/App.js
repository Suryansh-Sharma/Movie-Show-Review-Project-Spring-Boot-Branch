import './App.css';


import { Route, BrowserRouter as Router, Routes } from "react-router-dom";

import { useContext, useState } from 'react';
import { ToastContainer } from 'react-toastify';
import 'slick-carousel/slick/slick-theme.css';
import 'slick-carousel/slick/slick.css';
import AllCategories from './Components/Categories/Card';
import PageNotFound from './Components/Exception/PageNotFound';
import Footer from "./Components/Footer/Footer";
import FullDetail from "./Components/FullDetails/FullDetail";
import Movie from "./Components/Movie/Movie";
import NavigationBar from "./Components/NavigationBar/NavigationBar";
import NewMapApi from './Components/NewsSection/NewMapApi';
import SearchBar from "./Components/SearchBar/SearchBar";
import Login from './Components/Security/Login';
import PrivateLogin from './Components/Security/PrivateLogin';
import PrivateProfileRoute from './Components/Security/PrivateProfileRoute';
import PrivateRouter from './Components/Security/PrivateRouter';
import Profile from './Components/Security/Profile';
import SignUp from './Components/Security/SignUp';
import Slides from "./Components/Slides/Slides";
import Top10Movies from "./Components/TopMovies/Top10Movies";
import Upload from "./Components/UploadShow/Upload";
import Context, { MovieReviewContext } from './context/Context';
import { useEffect } from 'react';
import ScrollToTop from './Components/ScrollToTop';
import VerificationPage from './Components/Security/VerificationPage';
import AllUsers from './Components/Security/AllUsers';
import Review from './Components/Reviews/Reviews';
function App() {
  const {setUser,setLogin,isLogin,user} = useContext(MovieReviewContext);
  const[isLoading,setLoading] = useState(true);
  useEffect(()=>{
    setLoading(true);
    const data = localStorage.getItem('user-details')
    if(data!==null){
      const jsonUser = JSON.parse(data);
      const decoded_token = jsonUser.jwt_token;
      let currentDate = new Date();
      if(decoded_token.exp * 1000 < currentDate.getTime()){
        setLogin(false);
        setUser({});
        localStorage.removeItem("user-details");
        console.log("Token Expired !!")
        return;
      }

      setUser(jsonUser);
      setLogin(true);
    }
    setLoading(false)
  },[]);
  if(isLoading)return<h1>Please wait page is loading !!</h1>
  if(isLogin && !user.User.isVerified)return<VerificationPage/> 
  return (

    <div className={"App"}>
        <ToastContainer/>
        <Router>
	        <ScrollToTop/>
        <NavigationBar  />
        <SearchBar/>
            <Routes>
                <Route path={"/*"} element={<><Slides/><Top10Movies/><NewMapApi/></>}/>
                <Route path={"error404"} element={<PageNotFound/>}/>
                <Route path="search" element={<SearchBar/>} />
                <Route path="categories" element={<AllCategories/>}/>
                <Route path="movies/:path/:param" element={<Movie/>} />
                <Route path="review/:movie_id/:action" element={
                  <PrivateProfileRoute>
                    <Review/>
                  </PrivateProfileRoute>
                }/>
                <Route path="show/:id" element={<FullDetail /> }/>
                <Route path="all-users" element={
                  <PrivateRouter>
                    <AllUsers/>
                  </PrivateRouter>
                }/>
                <Route path="profile" element={
                  <PrivateProfileRoute>
                  <Profile/>
                </PrivateProfileRoute>
                }/>
                <Route path="login" element={
                  <PrivateLogin>
                  <Login/>
                </PrivateLogin>
                }/>
                <Route path="signUp" element={
                  <PrivateLogin>
                    <SignUp/>
                  </PrivateLogin>
                } />
                <Route path={"/uploadShow"} element={
                // <PrivateRouter>
                  <Upload/>
                // </PrivateRouter>
                
                }/>

            </Routes>
            <Footer/>
        </Router>
    </div>
  );
}

export default ()=>{
  return(
    <Context>
      <App/>
    </Context>
  );
};
