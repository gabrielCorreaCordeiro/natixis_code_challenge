import axios from 'axios';
import MockAdapter from 'axios-mock-adapter';

const mock = new MockAdapter(axios, { delayResponse: 500 }); 

// Mock POST /login
mock.onPost('http://localhost:8080/auth/login').reply((config) => {
  const { username, password } = JSON.parse(config.data);

  // Validate mock admin
  if (username === 'admin' && password === '123') {
    const fakeToken = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.fakepayload.sig';
    return [200, { token: fakeToken }];
  } else {
    return [401, { message: 'Credenciais inválidas' }];
  }
});

// Mock GET /items
mock.onGet('http://localhost:8080/items').reply((config) => {
  const authHeader = config.headers.Authorization;

  if (authHeader === 'Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.fakepayload.sig') {
    return [200, [
      { id: 1, name: 'Product A', price: 10 },
      { id: 2, name: 'Product B', price: 20 },
      { id: 3, name: 'Product C', price: 30 }
    ]];
  } else {
    return [403, { message: 'Not authorized' }];
  }
});
