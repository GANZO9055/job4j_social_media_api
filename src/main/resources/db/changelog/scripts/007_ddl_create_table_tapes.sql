CREATE TABLE tapes (
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(id),
    post_id INT REFERENCES posts(id),
    created_post TIMESTAMP
);