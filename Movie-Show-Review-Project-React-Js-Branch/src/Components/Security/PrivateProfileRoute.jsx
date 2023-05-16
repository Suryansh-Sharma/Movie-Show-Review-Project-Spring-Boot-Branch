import React, {useContext} from 'react';
import {Navigate} from 'react-router-dom';
import {MovieReviewContext} from '../../context/Context';

export default function PrivateProfileRoute({children}) {

    const {isLogin} = useContext(MovieReviewContext);
    if (isLogin) {
        return children;
    }
    return (
        <div>
            <Navigate to="/"/>
        </div>
    )
}
