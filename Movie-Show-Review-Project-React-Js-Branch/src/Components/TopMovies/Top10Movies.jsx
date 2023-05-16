import React, {useContext, useEffect, useState} from 'react';
import {Carousel} from "../Carasouls/Carousel";
import axios from "axios";
import {MovieReviewContext} from '../../context/Context';

const Top10Movies = () => {
    const {baseUrl} = useContext(MovieReviewContext);
    const [randomMovies, setRandomMovies] = useState({result: []});
    const [actionMovies, setActionMovies] = useState({result: []});
    const [isLoading, setLoading] = useState(true);
    useEffect(() => {
        document.title = "Movie Show Review";
        handleFetchTopMovies();
        handleTopActionMovie();
    }, [])
    const handleFetchTopMovies = () => {
        setLoading(true);
        axios.get(`${baseUrl}/api/movie/get-random-by-various-type/Random/random`)
            .then(res => {
                // console.log(res.data);
                setRandomMovies(res.data);
                setLoading(false);
            })
            .catch(error => {
                console.log(error);
            });
    }
    const handleTopActionMovie = () => {
        setLoading(true);
        axios.get(`${baseUrl}/api/movie/get-random-by-various-type/Genres/Comedy`)
            .then(res => {
                // console.log(res.data);
                setActionMovies(res.data);
                setLoading(false);
            })
            .catch(error => {
                console.log(error);
            });
    }
    if (isLoading) return <h1>Please wait page is loading</h1>
    return (
        <div>
            {
                <Carousel data={randomMovies} title={"TOP Collections"} time={4000} isAuto={true}></Carousel>
            }
            {
                <Carousel data={actionMovies} title={"TOP COMEDY MOVIES"} time={5000} isAuto={false}></Carousel>
            }

        </div>
    );
}

export default Top10Movies;