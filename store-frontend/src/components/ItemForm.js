import { useState } from 'react';

function ItemForm({ onCreate }) {
  const [item, setItem] = useState({ name: '', price: '' });

  const handleSubmit = (e) => {
    e.preventDefault();
    if (!item.name || !item.price) return;
    onCreate(item);
    setItem({ name: '', price: '' });
  };

  return (
    <form className="add-form-horizontal" onSubmit={handleSubmit}>
      <input
        type="text"
        placeholder="Name"
        value={item.name}
        onChange={(e) => setItem({ ...item, name: e.target.value })}
      />
      <input
        type="number"
        placeholder="Price"
        value={item.price}
        onChange={(e) => setItem({ ...item, price: e.target.value })}
      />
      <button type="submit">Create</button>
    </form>
  );
}

export default ItemForm;
