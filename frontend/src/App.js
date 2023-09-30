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
      const response = await fetch('http://localhost:8080/getVideoDetails?channelName=StoryRecapped');
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
    else if(data || data.items) {
      const channelResObj = data.items;
      console.log("we are inside renderData outputting resObj: ");
      console.log(channelResObj);

      return (
        <div>
          {channelResObj.map((item, index) => (
            <div key={index}>{item.snippet.description}</div>
          ))};
        </div>
      );
    }
    else {
      return <p> No data is available .... </p>
    }
  };

  return (
    <div className="App">
      <header className="App-header">
        

      <div>{renderData()}</div>

      </header>
    </div>
  );
}

export default App;
