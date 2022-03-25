alter table fact_param_func
    add constraint fk_strain_id
        foreign key ("strain_id")
            references strain ("id")
            ON DELETE CASCADE,
    add constraint fk_dependency_table_id
        foreign key ("dependency_table_id")
            references dependency_table ("id")
            ON DELETE CASCADE;

GO