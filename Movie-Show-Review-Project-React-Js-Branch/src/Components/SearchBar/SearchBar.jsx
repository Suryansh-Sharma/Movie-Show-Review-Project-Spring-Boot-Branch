import React, {useContext, useEffect, useState} from "react";
import "./SearchBar.css";
import axios from "axios";
import {useNavigate} from "react-router-dom";
import {MovieReviewContext} from "../../context/Context";

function SearchBar(props) {
    const [value, setValue] = useState("");
    const [data, setData] = useState({result: []});
    const {baseUrl} = useContext(MovieReviewContext);
    let navigate = useNavigate();
    useEffect(() => {
    }, []);
    const handleSearchApi = (event) => {
        const val = event.target.value;
        setValue(val);
        if (val === "") {
            setData({result: []});
            return;
        }
        axios
            .get(`${baseUrl}/api/movie/search-by-name/${val}`)
            .then((response) => {
                console.log(response.data);
                setData(response.data);
            })
            .catch((err) => {
                console.log(err);
            });
    };

    const onSearch = (searchTerm) => {
    };
    const handleResultClick = (id) => {
        navigate(`/show/${id}`);
        setData({result: []});
        setValue("");
    }
    const handleGenreClick = (genreName) => {
        navigate(`/movies/by-genre/${genreName}`);
        setData({result: []});
        setValue("");
    }
    return (
        <div className='Search'>
            <div className='search-container'>
                <div className='search-inner'>
                    <input type='text' value={value} onChange={handleSearchApi}
                           placeholder={"Search Movie Here"}
                           className={"seach-input-bar"}
                    />
                    {/* <button className='button' onClick={() => onSearch(value)}>
            {" "}
            Search{" "}
          </button> */}
                </div>
                <div className='dropdown'>
                    {data.result.map((item) => (
                        <div
                            className='dropdown-row'
                            key={item.name}
                        >
                            <div className='movie-name'

                                 onClick={() => handleResultClick(item.id)}
                            >{item.name}</div>
                            <div className='genres'>
                                {item.genres.map((genre) => (
                                    <span key={genre} onClick={
                                        () => handleGenreClick(genre)}>{genre}</span>
                                ))}
                            </div>
                        </div>
                    ))}
                </div>
            </div>
        </div>
    );
}

export default SearchBar;
