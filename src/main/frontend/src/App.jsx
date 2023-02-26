import React, { useState, useEffect, useCallback } from 'react';
import './App.css';
import axios from 'axios';
import {useDropzone} from 'react-dropzone';

const UserProfiles = () => {

  const [userProfiles, setUserProfiles] = useState([]);

  const fetchUserProfiles = () => {
    axios.get(`http://localhost:8080/api/v1/user-profile`).then(response => {
      console.log(response)
      setUserProfiles(response.data);
    });
  };

  useEffect(() => {
    fetchUserProfiles();
  }, []);

  return userProfiles.map((userProfile, index) => {
    return (
      <div key={index}>
        {/* todo: profile image */}
        <br />
        <br />
        <h1>{userProfile.userName}</h1>
        <p>{userProfile.userProfileId}</p>
        <Dropzone />
        <br />
      </div>
    );
  });
};

const Dropzone = () => {
  const onDrop = useCallback(acceptedFiles => {
    const file = acceptedFiles[0];
    console.log(file);
  }, [])
  const {getRootProps, getInputProps, isDragActive} = useDropzone({onDrop})

  return (
    <div {...getRootProps()}>
      <input {...getInputProps()} />
      {
        isDragActive ?
          <p>Drop the profile image here ...</p> :
          <p>Drag 'n' drop profile image, or click to select profile image</p>
      }
    </div>
  )
}

const App = () => {

  return (
    <div className="App">
      <UserProfiles />
    </div>);
};

export default App;