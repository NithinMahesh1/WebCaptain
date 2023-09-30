import React, { useState, useEffect } from 'react';
import './App.css';

function App() {
  const [data, setData] = useState([]);

  useEffect(() => {
    fetchData();
    dataView(data);
  }, []);

  const fetchData = async () => {
    try {
      const response = await fetch('http://localhost:8080/getVideoDetails?channelName=StoryRecapped');
      const jsonData = await response.json();
      console.log(jsonData);
      setData(jsonData);
    } catch (error) {
      console.error("Reporting Youtube API errors: " +error);
    }
  };

  var dataView = async (data) => {
    await fetchData();
    console.log("Printing the output of each item in loop: ");

    var dict = {};
    var channelResJSON = data.items;
    console.log(channelResJSON);

    const res_array = []; 
    for(let i in channelResJSON) { 
       res_array.push([i,channelResJSON[i]]); 
    };
    
    // for(let i=0; i<channelResJSON.length; i++) {
    //     console.log(channelResJSON[i]);
    //     dict[i] = channelResJSON[i];
    // }

    console.log(res_array);
    return res_array;
  }

  return (
    <div className="App">
      <header className="App-header">
        

      <div>{dataView(data)}</div>

      </header>
    </div>
  );
}

export default App;
