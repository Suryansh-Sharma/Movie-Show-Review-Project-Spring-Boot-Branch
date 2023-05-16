import React, {useContext, useEffect, useState} from "react";
import {toast} from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import {FormGroup, FormLabel} from "react-bootstrap";
import "./Review.css";
import axios from "axios";
import {useNavigate, useParams} from "react-router-dom";
import {MovieReviewContext} from "../../context/Context";

function Review() {
    let {movie_id, action} = useParams();
    let navigate = useNavigate();
    const {user, baseUrl} = useContext(MovieReviewContext);
    const [castData, setCastData] = useState();
    const [isLoading, setLoading] = useState(true);
    useEffect(() => {
        document.title = "Review"
        console.log(action);
        handleFetchData();
        if (action === "update") {
            handleGetSavedData();
        }
    }, [movie_id, action]);
    const handleFetchData = () => {
        axios.get(`${baseUrl}/api/movie/credits-by-movie-id/${movie_id}`)
            .then(response => {
                setCastData(response.data);
                setLoading(false);
                console.log(user);
            })
            .catch(error => {
                console.log(error);
            })
    }

    const handleGetSavedData = () => {
        axios.get(`${baseUrl}/api/movie/get-review-for-user/${movie_id}`,
            {
                headers: {Authorization: `Bearer ${user.jwt_token}`}
            }
        )
            .then(response => {
                setFormData(response.data);
                console.log(response.data);
                setLoading(false);
            })
            .catch(error => {
                console.log(error);
            })
    }

    const [formData, setFormData] = useState({
        id: 0,
        movieRating: 0,
        bestActor: '',
        plotRating: '',
        visualRatings: '',
        soundRating: '',
        recommend: '',
        otherComments: '',
    });
    const handleInputChange = (event) => {
        const {name, value} = event.target;
        setFormData((prevFormData) => ({
            ...prevFormData,
            [name]: value,
        }));
    };
    const handleFormSubmit = (event) => {
        event.preventDefault();
        // Extract the form data from the state
        const {movieRating, bestActor, plotRating, visualRatings, soundRating, recommend, otherComments} = formData;

        // Prepare the data for the POST API request
        const postData = {
            movieRating,
            bestActor,
            plotRating,
            visualRatings,
            soundRating,
            recommend,
            otherComments,
        };
        // Display the form data on the console
        console.log(postData);
        setLoading(true);
        let link = `${baseUrl}/api/movie/add-review/${movie_id}`;
        if (action === "update") {
            link = `${baseUrl}/api/movie/update-review-for-user/${formData.id}`
        }
        axios.post(link, {
                "movieRating": movieRating,
                "bestActor": bestActor,
                "plotRating": plotRating,
                "visualRatings": visualRatings,
                "soundRating": soundRating,
                "recommend": recommend,
                "otherComments": otherComments

            },
            {
                headers: {Authorization: `Bearer  ${user.jwt_token}`},
            })
            .then(response => {
                toast.success("🦄 Added your review successfully", {
                    position: "top-center",
                    autoClose: 5000,
                    hideProgressBar: false,
                    closeOnClick: true,
                    pauseOnHover: true,
                    draggable: true,
                    progress: undefined,
                });
                navigate("/show/" + movie_id);
            })
            .catch(error => {
                console.log(error)
                if (error.response && error.response.data) {
                    const {movieRating, bestActor} = error.response.data;
                    if (movieRating) {
                        toast.error(movieRating, {
                            position: "top-center",
                            autoClose: 5000,
                            hideProgressBar: false,
                            closeOnClick: true,
                            pauseOnHover: true,
                            draggable: true,
                            progress: undefined,
                        });
                    }
                    if (bestActor) {
                        toast.error(bestActor, {
                            position: "top-center",
                            autoClose: 5000,
                            hideProgressBar: false,
                            closeOnClick: true,
                            pauseOnHover: true,
                            draggable: true,
                            progress: undefined,
                        });
                    }
                }
            })
        setLoading(false)
    };
    if (isLoading) return <h1>Please wait page is loading !!</h1>
    return (
        <div className='container'>
            <h3 className={"reviewSectionTitle"}>Review</h3>
            <div className='row'>
                <div className='col-md-6'>
                    <form onSubmit={handleFormSubmit}>
                        {/* Movie Rating */}
                        <div className='form-group'>
                            <label htmlFor='exampleFormControlSelect1'>
                                On a scale of 1 to 10, how would you rate the movie?
                            </label>
                            <select className='form-control' id='exampleFormControlSelect1' onChange={handleInputChange}
                                    name={"movieRating"} value={formData.movieRating}>
                                <option>Select an option</option>
                                <option value={1}>1</option>
                                <option value={2}>2</option>
                                <option value={3}>3</option>
                                <option value={4}>4</option>
                                <option value={5}>5</option>
                                <option value={6}>6</option>
                                <option value={7}>7</option>
                                <option value={8}>8</option>
                                <option value={9}>9</option>
                                <option value={10}>10</option>
                            </select>
                        </div>
                        {/* Best Actor Performance */}
                        <div className='form-group'>
                            <label htmlFor='exampleFormControlSelect2'>
                                Which actor/actress delivered the best performance?
                            </label>
                            <select className='form-control' id='exampleFormControlSelect2' onChange={handleInputChange}
                                    name={"bestActor"}
                                    value={formData.bestActor}
                            >
                                <option value=''>Select an option</option>
                                {castData.cast.map((actor) => (
                                    <option key={actor.name} value={actor.name}>{actor.name}</option>
                                ))}
                            </select>
                        </div>
                        {/* Plot */}
                        <FormGroup className='form-group' value={formData.plotRating}>
                            <FormLabel>
                                Did you find the plot engaging and well-developed?
                            </FormLabel>
                            <div>
                                <div className='form-check flex-column'>
                                    <input
                                        className='form-check-input review-form-check'
                                        type='radio'
                                        name='plotRating'
                                        id='plotRatingCaptivating'
                                        value='captivating'
                                        required
                                        onChange={handleInputChange}
                                        checked={formData.plotRating === "captivating"}
                                    />
                                    <label
                                        className='form-check-label'
                                        htmlFor='plotRatingCaptivating'
                                    >
                                        Yes, it was captivating.
                                    </label>
                                </div>
                                <div className='form-check'>
                                    <input
                                        className='form-check-input review-form-check'
                                        type='radio'
                                        name='plotRating'
                                        id='plotRatingAverage'
                                        value='average'
                                        onChange={handleInputChange}
                                        checked={formData.plotRating === "average"}
                                    />
                                    <label
                                        className='form-check-label'
                                        htmlFor='plotRatingAverage'
                                    >
                                        It was average.
                                    </label>
                                </div>
                                <div className='form-check'>
                                    <input
                                        className='form-check-input review-form-check'
                                        type='radio'
                                        name='plotRating'
                                        id='plotRatingConfusing'
                                        value='confusing'
                                        onChange={handleInputChange}
                                        checked={formData.plotRating === "confusing"}
                                    />
                                    <label
                                        className='form-check-label'
                                        htmlFor='plotRatingConfusing'
                                    >
                                        No, it was confusing.
                                    </label>
                                </div>
                            </div>
                        </FormGroup>

                        {/* Visual Effects */}
                        <FormGroup className='form-group'>
                            <FormLabel>
                                How would you rate the visual effects in the movie?
                            </FormLabel>
                            <div>
                                <div className='form-check flex-column'>
                                    <input
                                        className='form-check-input review-form-check'
                                        type='radio'
                                        name='visualRatings'
                                        id='visualRatings'
                                        value='Excellent'
                                        required
                                        onChange={handleInputChange}
                                        checked={formData.visualRatings === "Excellent"}
                                    />
                                    <label
                                        className='form-check-label'
                                        htmlFor='plotRatingCaptivating'
                                    >
                                        Excellent.
                                    </label>
                                </div>
                                <div className='form-check'>
                                    <input
                                        className='form-check-input review-form-check'
                                        type='radio'
                                        name='visualRatings'
                                        id='visualRatings'
                                        value='Good'
                                        onChange={handleInputChange}
                                        checked={formData.visualRatings === "Good"}
                                    />
                                    <label
                                        className='form-check-label'
                                        htmlFor='plotRatingAverage'
                                    >
                                        Good.
                                    </label>
                                </div>
                                <div className='form-check'>
                                    <input
                                        className='form-check-input review-form-check'
                                        type='radio'
                                        name='visualRatings'
                                        id='visualRatings'
                                        value='Average'
                                        onChange={handleInputChange}
                                        checked={formData.visualRatings === "Average"}
                                    />
                                    <label
                                        className='form-check-label'
                                        htmlFor='plotRatingConfusing'
                                    >
                                        Average.
                                    </label>
                                </div>
                                <div className='form-check'>
                                    <input
                                        className='form-check-input review-form-check'
                                        type='radio'
                                        name='visualRatings'
                                        id='visualRatings'
                                        value='Poor'
                                        onChange={handleInputChange}
                                        checked={formData.visualRatings === "Poor"}
                                    />
                                    <label
                                        className='form-check-label'
                                        htmlFor='plotRatingConfusing'
                                    >
                                        Poor.
                                    </label>
                                </div>
                            </div>
                        </FormGroup>

                        {/* Sound Track*/}
                        <FormGroup className='form-group'>
                            <FormLabel>
                                Did you enjoy the movie's soundtrack and background music?
                            </FormLabel>
                            <div>
                                <div className='form-check flex-column'>
                                    <input
                                        className='form-check-input review-form-check'
                                        type='radio'
                                        name='soundRating'
                                        id='soundRating'
                                        value='Yes'
                                        required
                                        onChange={handleInputChange}
                                        checked={formData.soundRating === "Yes"}
                                    />
                                    <label
                                        className='form-check-label'
                                        htmlFor='plotRatingCaptivating'
                                    >
                                        Yes, it enhanced the viewing experience
                                    </label>
                                </div>
                                <div className='form-check'>
                                    <input
                                        className='form-check-input review-form-check'
                                        type='radio'
                                        name='soundRating'
                                        id='soundRating'
                                        value='Ok'
                                        onChange={handleInputChange}
                                        checked={formData.soundRating === "Ok"}
                                    />
                                    <label
                                        className='form-check-label'
                                        htmlFor='plotRatingAverage'
                                    >
                                        It was okay.
                                    </label>
                                </div>
                                <div className='form-check'>
                                    <input
                                        className='form-check-input review-form-check'
                                        type='radio'
                                        name='soundRating'
                                        id='soundRating'
                                        value='No'
                                        onChange={handleInputChange}
                                        checked={formData.soundRating === "No"}
                                    />
                                    <label
                                        className='form-check-label'
                                        htmlFor='plotRatingConfusing'
                                    >
                                        No, it didn't leave an impact
                                    </label>
                                </div>
                            </div>
                        </FormGroup>

                        {/* Recommand Movie*/}
                        <FormGroup className='form-group'>
                            <FormLabel>
                                Would you recommend this movie to others?
                            </FormLabel>
                            <div>
                                <div className='form-check flex-column'>
                                    <input
                                        className='form-check-input review-form-check'
                                        type='radio'
                                        name='recommend'
                                        id='recommend'
                                        value='Definitely'
                                        required
                                        onChange={handleInputChange}
                                        checked={formData.recommend === "Definitely"}
                                    />
                                    <label
                                        className='form-check-label'
                                        htmlFor='plotRatingCaptivating'
                                    >
                                        Definitely
                                    </label>
                                </div>
                                <div className='form-check'>
                                    <input
                                        className='form-check-input review-form-check'
                                        type='radio'
                                        name='recommend'
                                        id='recommend'
                                        value='Maybe'
                                        onChange={handleInputChange}
                                        checked={formData.recommend === "Maybe"}
                                    />
                                    <label
                                        className='form-check-label'
                                        htmlFor='plotRatingAverage'
                                    >
                                        Maybe
                                    </label>
                                </div>
                                <div className='form-check'>
                                    <input
                                        className='form-check-input review-form-check'
                                        type='radio'
                                        name='recommend'
                                        id='recommend'
                                        value='No'
                                        onChange={handleInputChange}
                                        checked={formData.recommend === "No"}
                                    />
                                    <label
                                        className='form-check-label'
                                        htmlFor='plotRatingConfusing'
                                    >
                                        No
                                    </label>
                                </div>
                            </div>
                        </FormGroup>

                        <div className='form-group'>
                            <label htmlFor='exampleInputPassword1'>Any other thing about movie</label>
                            <textarea className="form-control" id="exampleFormControlTextarea1" rows="3"
                                      placeholder={"Optional"}
                                      onChange={handleInputChange} name={"otherComments"} value={formData.otherComments}
                            ></textarea>
                        </div>
                        <button type='submit' className='btn-primary'>
                            Submit
                        </button>
                    </form>
                </div>
            </div>
        </div>
    );
}

export default Review;
