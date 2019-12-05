USE `scoring_utp_db`;

INSERT INTO `security_questions`(`security_id`,`security_text`) VALUES (1,'Where were you when you had your first kiss?'), (2,'In what city or town was your first job?'),(3,'What was your childhood nickname?'), (4,'What is the name of your favorite childhood friend?');

select pr.professor_id, count(*) as scorings, avg(sc.facility) as facility , avg(sc.`help`) as `help`,
 avg(sc.clarity) as clarity from scorings as sc inner join professors as pr on sc.professor_id = pr.professor_id;
 
 select pr.professor_id,
 CONCAT(pr.first_name,' ',pr.last_name) AS professor_full_name, 
 IF(count(sc.scoring_id) > 0, count(sc.scoring_id) , null) as scorings, 
 ROUND(avg(sc.facility),1) as facility_avg , 
 ROUND(avg(sc.`help`),1) as help_avg,
 ROUND(avg(sc.clarity),1) as clarity_avg, 
 ROUND((avg(sc.clarity) + avg(sc.facility) + avg(sc.`help`)) / 3,1) as total_avg 
 from scorings as sc right join professors as pr on sc.professor_id = pr.professor_id group by pr.professor_id;
