-- Run this AFTER starting the app once (so Hibernate creates the tables).
-- Sample travel packages
INSERT INTO packages (destination, description, price, duration_days, image_url) VALUES
('Goa Beach Escape', 'Relax on golden beaches with a 4-night stay, water sports, and sunset cruises included.', 12999.00, 4, 'https://images.unsplash.com/photo-1512343879784-a960bf40e7f2'),
('Kerala Backwaters', 'A serene houseboat experience through the Alleppey backwaters with authentic Kerala cuisine.', 15999.00, 5, 'https://images.unsplash.com/photo-1602216056096-3b40cc0c9944'),
('Himalayan Manali Trip', 'Adventure trip covering Solang Valley, Rohtang Pass, and local sightseeing.', 10999.00, 3, 'https://images.unsplash.com/photo-1626621341517-bbf3d9990a23'),
('Rajasthan Heritage Tour', 'Explore Jaipur, Udaipur, and Jodhpur forts and palaces with a private guide.', 18999.00, 6, 'https://images.unsplash.com/photo-1477587458883-47145ed94245');

-- Note: to create an admin user, register normally through the app first,
-- then run this to promote that account to admin (replace the email):
-- UPDATE users SET role = 'ROLE_ADMIN' WHERE email = 'youradminemail@example.com';
