FROM postgres
ENV POSTGRES_PASSWORD postgres
ENV POSTGRES_USER admin
EXPOSE 5432
COPY VTubers_DB.sql /docker-entrypoint-initdb.d/
COPY Mead_DB.sql /docker-entrypoint-initidb.d/