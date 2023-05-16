import Slider from "react-slick";
import "./Carousel.css";
import LeftArrow from "../../icons/icons8-back-to-96.png";
import RightArrow from "../../icons/previcon.png";
import {useNavigate} from "react-router-dom";
import {MovieReviewContext} from "../../context/Context";
import {useContext} from "react";

export const Carousel = ({data, title, time, isAuto}) => {
    const {posterUrl} = useContext(MovieReviewContext);
    const randomClasses = ["c1", "c2", "c3", "c4", "c5", "c6"]
    const SlickArrowLeft = ({currentSlide, slideCount, ...size}) => (
        <img src={LeftArrow} alt='prevArrow' {...size} />
    );

    const SlickArrowRight = ({currentSlide, slideCount, ...props}) => (
        <img src={RightArrow} alt='nextArrow' {...props} />
    );
    const settings = {
        prevArrow: <SlickArrowLeft className='right-arrow'/>,
        nextArrow: <SlickArrowRight/>,
        infinite: true,
        slidesToShow: 4,
        adaptiveHeight: true,
        slidesToScroll: 2,
        autoplay: isAuto,
        speed: 1200,
        autoplaySpeed: time,
        responsive: [
            {
                breakpoint: 1024,
                settings: {
                    slidesToShow: 2,
                    slidesToScroll: 1,
                },
            },
            {
                breakpoint: 600,
                settings: {
                    slidesToShow: 2,
                    slidesToScroll: 2,
                    initialSlide: 2,
                },
            },
            {
                breakpoint: 480,
                settings: {
                    slidesToShow: 1,

                    slidesToScroll: 1,
                },
            },
        ],
    };
    let navigate = useNavigate();
    return (
        <div className='carousel'>
            <div>
                <h2 className='title'>{title}</h2>
                <Slider {...settings} className={"card-slider"}>
                    {data.result.map((movie) => (
                        <div className={`card  ${randomClasses[Math.floor(Math.random() * randomClasses.length)]}`}
                             key={movie.id}>
                            <img
                                className={"card-img"}
                                src={`${posterUrl}${movie.posterPath}`}
                                alt=''
                            />
                            <span className='card-titles'>{movie.originalTitle}</span>
                            <button
                                onClick={() => {
                                    navigate("/show/" + movie.id);
                                }}
                                className='btn card-btn'
                            >
                                Read More.
                            </button>
                        </div>
                    ))}
                </Slider>
            </div>
        </div>
    );
};
