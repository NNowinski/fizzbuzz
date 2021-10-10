CREATE TABLE statistic
  (
     number_1      INTEGER NOT NULL,
     number_2      INTEGER NOT NULL,
     limit_of_list INTEGER NOT NULL,
     str1          VARCHAR(255) NOT NULL,
     str2          VARCHAR(255) NOT NULL,
     count         INTEGER NOT NULL,
     PRIMARY KEY (number_1, number_2, limit_of_list, str1, str2)
  ) 