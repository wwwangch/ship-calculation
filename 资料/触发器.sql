

create trigger tri_general_table_definition_insert after insert
on general_table_definition for each row
begin
insert into `south_sea_test`.general_table_definition select * from `south_sea`.general_table_definition where id = new.id;
end

create trigger tri_general_table_definition_update after update
on general_table_definition for each row
begin
replace into `south_sea_test`.general_table_definition select * from `south_sea`.general_table_definition where id = new.id;
end


create trigger tri_general_table_definition_delete after delete
on general_table_definition for each row
begin
delete from `south_sea_test`.general_table_definition where id = old.id;
end


create trigger tri_general_column_definition_insert after insert
on general_column_definition for each row
begin
insert into `south_sea_test`.general_column_definition select * from `south_sea`.general_column_definition where id = new.id;
end

create trigger tri_general_column_definition_update after update
on general_column_definition for each row
begin
replace into `south_sea_test`.general_column_definition select * from `south_sea`.general_column_definition where id = new.id;
end


create trigger tri_general_column_definition_delete after delete
on general_column_definition for each row
begin
delete from `south_sea_test`.general_column_definition where id = old.id;
end