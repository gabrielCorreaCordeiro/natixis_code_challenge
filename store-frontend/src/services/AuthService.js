import axios from 'axios';

const API = axios.create({
  baseURL: 'http://localhost:8080',
  headers: {
    Authorization: `Bearer ${localStorage.getItem('token')}`
  }
});

export const login = (username, password) => API.post('/auth/login',  {
        username,
        password
    });
export const register = (item) => API.post('/auth/register', item);
