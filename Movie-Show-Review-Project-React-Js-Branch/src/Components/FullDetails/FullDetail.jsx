import Axios from "axios";
import React, {useContext, useEffect, useState} from "react";
import {useNavigate, useParams} from "react-router-dom";
import "./FullDetails.css";
import {MovieReviewContext} from "../../context/Context";

const fakeApi = require("./SaveApiBody.json");

function FullDetails() {
    let {id} = useParams();
    let navigate = useNavigate();
    const {baseUrl, posterUrl, isLogin, user} = useContext(MovieReviewContext);
    const [loading, setLoading] = useState(true);
    const [isReviewed, setIsReviewed] = useState(true);
    const [movie, setMovie] = useState(fakeApi);
    const [casts, setCast] = useState({
        cast: [],
    });
    const [isParentLoaded, setIsParentLoaded] = useState(false);
    useEffect(() => {
        setLoading(true);
        Axios.get(`${baseUrl}/api/movie/full-detail-by-id/${id}`)
            .then((response) => {
                setMovie(response.data);
                document.title = response.data.originalTitle;
                setIsParentLoaded(true);
            })
            .catch((err) => {
                navigate("/error404");
            });
        Axios.get(`${baseUrl}/api/movie/credits-by-movie-id/${id}`)
            .then((response) => {
                setCast(response.data);
            })
            .catch((error) => {
                console.log(error);
            });
        setLoading(false);
        handleCheckMovieReviewedByUser();
    }, [id]);
    const handleTitleClick = () => {
        if (movie.homepage === "") return;
        window.location.href = movie.homepage;
    };
    const handleCheckMovieReviewedByUser = async () => {
        if (!isLogin) return;
        Axios.get(`${baseUrl}/api/movie/check-reviewed-by-user/${id}`, {
            headers: {Authorization: `Bearer ${user.jwt_token}`},
        })
            .then((res) => {
                console.log(res.data);
                setIsReviewed(res.data);
            })
            .catch((error) => {
                console.log(error);
            });
    };
    if (loading === true) return <h1>Please wait page is loading</h1>;
    return (
        <div className={"FullDetails"}>
            <h3 className='FullDetails-title' onClick={handleTitleClick}>
                {movie.originalTitle}
            </h3>

            <img
                className={"FullDetails-image"}
                src={`${posterUrl}${movie.backdropPath}`}
                alt={""}
            />

            <span className={"des1"}>{movie.overview}</span>
            <a
                className={"readmore"}
                href={`https://www.imdb.com/title/${movie.imdbId}/`}
                target={"_blank"}
                rel='noopener noreferrer'
            >
                Read More
            </a>
            <span className={"type-heading"}>Ratings</span>
            <span>
        <span className={"type"}>:- {movie.voteAverage}</span>
      </span>

            <div className='FullDetails-category'>
                {movie.genres.map((item) => (
                    <span
                        key={item.id}
                        className={"cat1"}
                        onClick={() => navigate("/movies/" + "by-genre/" + item.name)}
                    >
            {item.name}
          </span>
                ))}
            </div>
            {/* End of page Upto Genres */}
            {isLogin && isReviewed ? (
                <button
                    type='button'
                    className='showReviewBtn'
                    onClick={() => navigate("/review/" + movie.id + "/update")}
                >
                    Update you review
                </button>
            ) : (
                <button
                    type='button'
                    className='showReviewBtn'
                    onClick={() => navigate("/review/" + movie.id + "/add-new")}
                >
                    Add Review
                </button>
            )}
            {/* Left and Right Grid page */}
            <h3 className={"Film-Credits-title"}>Film Credits</h3>

            <div className={"Movie-Credits"}>
                <div className='FulDetailsLeft'>
                    <div className='leftCard'>
                        <div className='Film-Credits-top'>
                            <img
                                className={"Film-Credits-img"}
                                src={`${posterUrl}${movie.belongsToCollection.backdropPath}`}
                                alt=''
                            />
                        </div>

                        <div className={"Film-Credits-bottom"}>
                            <span className={"date"}>{movie.releaseDate}</span>
                            <span className={"rating"}>
                Rating : {movie.voteAverage} out of 10
              </span>
                            <span className={"duration"}>
                Duration : {movie.runtime} minutes
              </span>
                        </div>
                    </div>
                    <span style={{color: "white", textAlign: "center"}}>
            Production Companies
          </span>
                    <div>
                        {movie.productionCompanies.map((pc) => (
                            <div
                                className='cast-card pc-card'
                                key={pc.id}
                                onClick={() => {
                                    navigate("/movies/" + "by-production/" + pc.name);
                                }}
                            >
                                <img
                                    className='cast-image'
                                    src={`${posterUrl}${pc.logoPath}`}
                                    alt='not available'
                                />
                                <div className='cast-details'>
                                    <h3 className='cast-name'>{pc.name}</h3>
                                    <p className='cast-character'>{pc.originCountry}</p>
                                </div>
                            </div>
                        ))}
                    </div>
                </div>

                <div className='Film-Credits-right'>
                    <h2 className={"Film-Credits-right-cast"}>Top Cast </h2>
                    {casts.cast.map((item) => (
                        <div
                            className='cast-card'
                            key={item.id}
                            onClick={() =>
                                navigate("/movies/" + "by-person-name/" + item.name)
                            }
                        >
                            <img
                                className='cast-image'
                                src={`${posterUrl}${item.profilePath}`}
                                alt='Cast member name'
                            />
                            <div className='cast-details'>
                                <h3 className='cast-name'>{item.name}</h3>
                                <p className='cast-character'>{item.character}</p>
                            </div>
                        </div>
                    ))}
                </div>
            </div>
            {isParentLoaded ? (
                <div className='commentSection'>{/* <Comments id={movie.id}/> */}</div>
            ) : null}
        </div>
    );
}

export default FullDetails;
