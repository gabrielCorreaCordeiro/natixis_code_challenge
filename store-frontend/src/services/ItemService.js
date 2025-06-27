import axios from 'axios';

const API = axios.create({
  baseURL: 'http://localhost:8080',
  headers: {
    Authorization: `Bearer ${localStorage.getItem('token')}`
  }
});

export const getItems = () => API.get('/items');
export const createItem = (item) => API.post('/items', item);
export const deleteItem = (id) => API.delete(`/items/${id}`);
//export const editItem = (id) => API.edit(`/items/${id}`);
