CREATE TABLE position (
    pos_id IDENTITY NOT NULL PRIMARY KEY,
    position NOT NULL VARCHAR(20)
)

CREATE TABLE chef (
    chef_id IDENTITY NOT NULL PRIMARY KEY,
    h_wage NUMERIC(2,2) NOT NULL,
    contact_no VARCHAR(13) NOT NULL,
    position INT,

    CONSTRAINT FK_CHEF_POS FOREIGN KEY (position) REFERENCES position (pos_id)
)

CREATE TABLE shift (
    shift_id IDENTITY NOT NULL PRIMARY KEY,
    date_of DATE NOT NULL,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,
    break_duration_h NUMERIC(1,2)
    chef INT,

    CONSTRAINT FK_SHIFT_CHEF FOREIGN KEY (chef) REFERENCES chef (chef_id)
)