import React, { useState, useEffect } from 'react';
import './App.css';

function App() {
  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetchData();
  }, []);

  const fetchData = async () => {
    try {
      const response = await fetch('http://localhost:8080/getChannelDetails?channelName=WhistlinDiesel');
      const jsonData = await response.json();

      setData(jsonData);
      setLoading(false);
    } catch (error) {
      console.error("Reporting Youtube API errors: " +error);
    }
  };

    

  const renderData = () => {
    if(loading) {
      return <p> loading .... </p>
    } 
    else if(data && data.items) {
      const channelResObj = data.items;

      console.log("we are inside renderData outputting resObj: ");
      console.log(channelResObj);

      return (
        <div className='appWrapper'>
          {channelResObj.map((item, index) => (
            <div key={index}>
              
              <div className='titleAndDesc'>
                <img src={item.snippet.thumbnails.medium.url} alt="Thumbnail"></img>
                <div className='ChannelDescription'>{item.snippet.title}</div>
              </div>

              <div className='titleAndPubDate'>
                <div className='ChannelTitle'>{item.snippet.channelTitle}</div>
                <div className='PublishTime'>{publishDateConverter(item)}</div>
              </div>

            </div>
          ))}
        </div>
        
      );
    }
    else {
      return <p> No data is available .... </p>;
    }
  };

  const publishDateConverter = (item) => {
    console.log("date converter");
    console.log(item);

    if(!item || !item.snippet || !item.snippet.publishTime) {
      return "No data available"
    }
    // 2023-08-18T22:02:53Z
    const publishTime = item.snippet.publishTime;
    const pubDate = new Date(publishTime);
    const currentDate = new Date();

    var oneDay = 1000 * 60 * 60 * 24;
    var calculateDiff = Math.round(currentDate.getTime() - pubDate.getTime()) / (oneDay);
    var roundedVal = calculateDiff.toString().charAt(0);
    var res = roundedVal + " days ago";

    if(roundedVal === "1") {
      res = roundedVal + " day ago";
    }

    return res;
  }

  return (
    <div className="App">
      <header className="App-header">
        

        <div>{renderData()}</div>

      </header>
    </div>
  );
}

export default App;
