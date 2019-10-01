 --  This script will add when serverstartup or comments this and run the Junit to insert records

INSERT INTO USERS (user_name, age) VALUES('rakesh', 24);
  
INSERT INTO FOLLOWS (follow_name) VALUES ('Rick'),('Sam'),('Rob'),('Mick');

INSERT INTO TWEETS (user_id, follow_id, msg_txt) VALUES(1, 1, 'hi'),(1, 2, 'hello'),(1, 3, 'how r u'),(1, 4, 'doing good');

-- SELECT * FROM users e JOIN tweets r ON e.user_id=r.user_id JOIN follows d ON r.follow_id=d.follow_id;
-- SELECT e.user_id,e.user_name ,e.age, d.follow_name, r.msg_txt, r.pub_date FROM USERS e JOIN TWEETS r ON e.user_id=r.user_id JOIN FOLLOWS d ON r.follow_id=d.follow_id where e.user_id=1 ORDER BY r.pub_date DESC ;