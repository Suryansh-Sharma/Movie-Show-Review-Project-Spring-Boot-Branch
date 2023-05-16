/**
 * This File is Use for More Movie By categories
 * It Uses Paging Sorting etc.
 */
import Axios from "axios";
import { React, useContext, useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { MovieReviewContext } from "../../context/Context";
import "./Movie.css";
function Movies() {
  const [currPage, setCurrPage] = useState(0);
  const [isLoading, setLoading] = useState(true);
  const { baseUrl, posterUrl } = useContext(MovieReviewContext);

  let navigate = useNavigate();
  let { path, param, title } = useParams();
  const [data, setData] = useState({
    page: 0,
    result: [],
    total_pages: 0,
    total_results: 0,
  });

  useEffect(() => {
    document.title=param +" "+ "Movies";
    fetchMovie();
  }, [path, param,setCurrPage,currPage]);

  const fetchMovie = () => {
    setLoading(true);
    var link = `${baseUrl}/api/movie/${path}/${param}?pageNumber=${currPage}`;
    if(path==="most_voted"){
      link = `${baseUrl}/api/movie/get-random-by-various-type/TopVoted/TopVoted?pageNo=${currPage}`
    }
    Axios.get(link)
      .then((response) => {
        setData(response.data);
        setLoading(false);

      })
      .catch((err) => {
        console.log(err)
      });
  };

  const prevButton = () => {
    if (currPage > 0) {
      setCurrPage(currPage - 1);
      // fetchMovie();
    } else alert("No Previous Page is Available !!");
  };
  const nextButton = () => {
    if(currPage < data.total_pages-1 )
    {
      setCurrPage(currPage + 1);
      // fetchMovie();
    }else{
      alert("This is last page ");
    }
  };
  if (isLoading) return <h1>Please wait untill page is loading</h1>;
  return (
    <div className='Movie'>
      <h1 className={"Movie-title"}>{param}</h1>

      <div className='Movie-Cards'>
        {data.result.map((response) => (
          <div className='Card' key={response.id}>
            <img
              className='Card-img'
              onClick={() => {
                navigate("/show/" + response.id);
              }}
              src={`${posterUrl}${response.posterPath}`}
              alt={response.originalTitle}
            />
            <h4 className='Card-title'>{response.originalTitle}</h4>
          </div>
        ))}
      </div>

      <div className='Movie-Paging'>
        <span className='Movie-Paging-Prev' onClick={prevButton}>
          Previous Page
        </span>
        <span className='Movie-Paging-Next' onClick={nextButton}>
          Next Page
        </span>
      </div>
    </div>
  );
}

export default Movies;
