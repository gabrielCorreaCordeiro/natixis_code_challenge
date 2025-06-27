import { useEffect, useState } from 'react';
import { getItems } from '../../services/ItemService';
import ItemForm from '../../components/ItemForm';
import ItemList from '../../components/ItemList';
import './Home.css';

function Home() {
  const [items, setItems] = useState([]);

  useEffect(() => {
    getItems()
      .then(res => setItems(res.data))
      .catch(err => console.error('Error when querying for items', err));
  }, []);

  const handleCreate = (item) => {
    const newItem = { ...item, id: Date.now() };
    setItems(prev => [...prev, newItem]);
  };

  const handleDelete = (id) => {
    setItems(prev => prev.filter(item => item.id !== id));
  };

  const handleEdit = (id, updatedItem) => {
  setItems(prev =>
    prev.map(item => item.id === id ? { ...item, ...updatedItem } : item)
  );
};

  return (
    <div className="home-wrapper">
      <ItemForm onCreate={handleCreate} />
      <ItemList items={items} onEdit={handleEdit} onDelete={handleDelete} />
    </div>
  );
}

export default Home;
