import React, {useEffect} from "react";
import "./Card.css";
import {useNavigate} from "react-router-dom";

const AllCategories = () => {
    let navigate = useNavigate();
    useEffect(() => {
        document.title = "All Categories"
    }, []);
    const handleRoute = (location) => {
        navigate(`/movies/by-genre/${location}`);
    }
    return (

        <div>
            <div className='ag-format-container'>
                <div className='ag-courses_box'>
                    <div className='ag-courses_item'
                         onClick={() => handleRoute("Action")}
                    >
            <span className='ag-courses-item_link'>
              <div className='ag-courses-item_bg'></div>

              <div className='ag-courses-item_title'>
                Action
              </div>

              <div className='ag-courses-item_date-box'>
                View:
                <span className='ag-courses-item_date'>Movies</span>
              </div>
            </span>
                    </div>

                    <div className='ag-courses_item'
                         onClick={() => handleRoute("Comedy")}
                    >
            <span className='ag-courses-item_link'>
              <div className='ag-courses-item_bg'></div>

              <div className='ag-courses-item_title'>
                Comedy
              </div>

              <div className='ag-courses-item_date-box'>
                View:
                <span className='ag-courses-item_date'>Movies</span>
              </div>
            </span>
                    </div>

                    <div className='ag-courses_item'
                         onClick={() => handleRoute("Fantasy")}
                    >
            <span href='#' className='ag-courses-item_link'>
              <div className='ag-courses-item_bg'></div>

              <div className='ag-courses-item_title'>
                Fantasy
              </div>

              <div className='ag-courses-item_date-box'>
                View:
                <span className='ag-courses-item_date'>Movies</span>
              </div>
            </span>
                    </div>

                    <div className='ag-courses_item'
                         onClick={() => handleRoute("Drama")}
                    >
                        <a href='#' className='ag-courses-item_link'>
                            <div className='ag-courses-item_bg'></div>

                            <div className='ag-courses-item_title'>Drama</div>

                            <div className='ag-courses-item_date-box'>
                                View:
                                <span className='ag-courses-item_date'>Movies</span>
                            </div>
                        </a>
                    </div>

                    <div className='ag-courses_item'
                         onClick={() => handleRoute("Adventure")}
                    >
                        <a href='#' className='ag-courses-item_link'>
                            <div className='ag-courses-item_bg'></div>

                            <div className='ag-courses-item_title'>Adventure</div>

                            <div className='ag-courses-item_date-box'>
                                View:
                                <span className='ag-courses-item_date'>Movies</span>
                            </div>
                        </a>
                    </div>

                    <div className='ag-courses_item'
                         onClick={() => handleRoute("Science Fiction")}
                    >
                        <a href='#' className='ag-courses-item_link'>
                            <div className='ag-courses-item_bg'></div>

                            <div className='ag-courses-item_title'>
                                Science Fiction
                            </div>
                        </a>
                    </div>

                    <div className='ag-courses_item'
                         onClick={() => handleRoute("Crime")}
                    >
                        <a href='#' className='ag-courses-item_link'>
                            <div className='ag-courses-item_bg'></div>
                            <div className='ag-courses-item_title'>Crime</div>
                        </a>

                    </div>

                    <div className='ag-courses_item'
                         onClick={() => handleRoute("Thriller")}
                    >
                        <a href='#' className='ag-courses-item_link'>
                            <div className='ag-courses-item_bg'></div>

                            <div className='ag-courses-item_title'>Thriller</div>

                            <div className='ag-courses-item_date-box'>
                                View:
                                <span className='ag-courses-item_date'>Movies</span>
                            </div>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default AllCategories;
