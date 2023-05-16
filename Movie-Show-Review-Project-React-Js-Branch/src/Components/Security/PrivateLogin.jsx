import React, {useContext} from 'react';
import {Navigate} from 'react-router-dom';
import {MovieReviewContext} from '../../context/Context';

export default function PrivateLogin({children}) {

    const {isLogin} = useContext(MovieReviewContext);
    console.log(isLogin)
    if (!isLogin) {
        return children;
    }
    return (
        <div>
            <Navigate to="/"/>
        </div>
    )
}
