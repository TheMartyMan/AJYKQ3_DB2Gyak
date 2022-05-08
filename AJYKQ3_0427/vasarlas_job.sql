BEGIN

DBMS_SCHEDULER.CREATE_JOB (
 job_name => 'vasarlas_job',
 job_type => 'STORED_PROCEDURE',
 job_action => 'VDbKiir',
 start_date => SYSTIMESTAMP,
 enabled => true,
 repeat_interval => 'FREQ=MINUTELY; INTERVAL=1',
 comments => 'Vasarlas db');
 
END;
