BEGIN
  dbms_scheduler.drop_job(job_name => 'vasarlas_job');
END;
