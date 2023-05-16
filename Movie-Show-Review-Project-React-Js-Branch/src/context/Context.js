import {createContext, useState} from "react";

export const MovieReviewContext = createContext();
const Context = ({children}) => {
    const baseUrl = "http://192.168.0.192:8080";
    const posterUrl = "https://image.tmdb.org/t/p/original/"
    const [user, setUser] = useState(null);
    const [isLogin, setLogin] = useState(false);
    return <MovieReviewContext.Provider
        value={{
            isLogin,
            setLogin,
            user,
            setUser,
            baseUrl,
            posterUrl,
        }}>
        {children}
    </MovieReviewContext.Provider>

}
export default Context;