import React, { useContext } from 'react';
import { Navigate } from 'react-router-dom';
import { MovieReviewContext } from '../../context/Context';
export default function PrivateRouter({children}) {

    const {user} = useContext(MovieReviewContext);
    if(user.User.role==="ADMIN" || user.role==="MANAGER"){
        return children;
    }

    return (
        <Navigate to="/"/>
    )
}
