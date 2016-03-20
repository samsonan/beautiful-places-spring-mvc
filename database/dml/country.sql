--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.0
-- Dumped by pg_dump version 9.5.0

-- Started on 2016-03-20 19:59:18

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

SET search_path = public, pg_catalog;

--
-- TOC entry 2141 (class 0 OID 16628)
-- Dependencies: 191
-- Data for Name: country; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO country VALUES ('BJ', 'Benin', 'Africa', 'BEN', 'Western Africa', 'Porto-Novo', 'WAF');
INSERT INTO country VALUES ('BF', 'Burkina Faso', 'Africa', 'BFA', 'Western Africa', 'Ouagadougou', 'WAF');
INSERT INTO country VALUES ('CI', 'Ivory Coast', 'Africa', 'CIV', 'Western Africa', 'Yamoussoukro', 'WAF');
INSERT INTO country VALUES ('BI', 'Burundi', 'Africa', 'BDI', 'Eastern Africa', 'Bujumbura', 'EAF');
INSERT INTO country VALUES ('KM', 'Comoros', 'Africa', 'COM', 'Eastern Africa', 'Moroni', 'EAF');
INSERT INTO country VALUES ('DJ', 'Djibouti', 'Africa', 'DJI', 'Eastern Africa', 'Djibouti', 'EAF');
INSERT INTO country VALUES ('ER', 'Eritrea', 'Africa', 'ERI', 'Eastern Africa', 'Asmara', 'EAF');
INSERT INTO country VALUES ('FJ', 'Fiji', 'Oceania', 'FJI', 'Melanesia', 'Suva', 'MEL');
INSERT INTO country VALUES ('IO', 'British Indian Ocean Territory', 'Africa', 'IOT', 'Eastern Africa', 'Diego Garcia', 'EAF');
INSERT INTO country VALUES ('KE', 'Kenya', 'Africa', 'KEN', 'Eastern Africa', 'Nairobi', 'EAF');
INSERT INTO country VALUES ('MG', 'Madagascar', 'Africa', 'MDG', 'Eastern Africa', 'Antananarivo', 'EAF');
INSERT INTO country VALUES ('MZ', 'Mozambique', 'Africa', 'MOZ', 'Eastern Africa', 'Maputo', 'EAF');
INSERT INTO country VALUES ('MU', 'Mauritius', 'Africa', 'MUS', 'Eastern Africa', 'Port Louis', 'EAF');
INSERT INTO country VALUES ('MW', 'Malawi', 'Africa', 'MWI', 'Eastern Africa', 'Lilongwe', 'EAF');
INSERT INTO country VALUES ('CV', 'Cape Verde', 'Africa', 'CPV', 'Western Africa', 'Praia', 'WAF');
INSERT INTO country VALUES ('GH', 'Ghana', 'Africa', 'GHA', 'Western Africa', 'Accra', 'WAF');
INSERT INTO country VALUES ('GN', 'Guinea', 'Africa', 'GIN', 'Western Africa', 'Conakry', 'WAF');
INSERT INTO country VALUES ('GM', 'Gambia', 'Africa', 'GMB', 'Western Africa', 'Banjul', 'WAF');
INSERT INTO country VALUES ('GW', 'Guinea-Bissau', 'Africa', 'GNB', 'Western Africa', 'Bissau', 'WAF');
INSERT INTO country VALUES ('LR', 'Liberia', 'Africa', 'LBR', 'Western Africa', 'Monrovia', 'WAF');
INSERT INTO country VALUES ('ML', 'Mali', 'Africa', 'MLI', 'Western Africa', 'Bamako', 'WAF');
INSERT INTO country VALUES ('MR', 'Mauritania', 'Africa', 'MRT', 'Western Africa', 'Nouakchott', 'WAF');
INSERT INTO country VALUES ('NE', 'Niger', 'Africa', 'NER', 'Western Africa', 'Niamey', 'WAF');
INSERT INTO country VALUES ('NG', 'Nigeria', 'Africa', 'NGA', 'Western Africa', 'Abuja', 'WAF');
INSERT INTO country VALUES ('SN', 'Senegal', 'Africa', 'SEN', 'Western Africa', 'Dakar', 'WAF');
INSERT INTO country VALUES ('SL', 'Sierra Leone', 'Africa', 'SLE', 'Western Africa', 'Freetown', 'WAF');
INSERT INTO country VALUES ('TG', 'Togo', 'Africa', 'TGO', 'Western Africa', 'Lomé', 'WAF');
INSERT INTO country VALUES ('ET', 'Ethiopia', 'Africa', 'ETH', 'Eastern Africa', 'Addis Ababa', 'EAF');
INSERT INTO country VALUES ('YT', 'Mayotte', 'Africa', 'MYT', 'Eastern Africa', 'Mamoudzou', 'EAF');
INSERT INTO country VALUES ('RE', 'Réunion', 'Africa', 'REU', 'Eastern Africa', 'Saint-Denis', 'EAF');
INSERT INTO country VALUES ('RW', 'Rwanda', 'Africa', 'RWA', 'Eastern Africa', 'Kigali', 'EAF');
INSERT INTO country VALUES ('SO', 'Somalia', 'Africa', 'SOM', 'Eastern Africa', 'Mogadishu', 'EAF');
INSERT INTO country VALUES ('SC', 'Seychelles', 'Africa', 'SYC', 'Eastern Africa', 'Victoria', 'EAF');
INSERT INTO country VALUES ('TZ', 'Tanzania', 'Africa', 'TZA', 'Eastern Africa', 'Dodoma', 'EAF');
INSERT INTO country VALUES ('UG', 'Uganda', 'Africa', 'UGA', 'Eastern Africa', 'Kampala', 'EAF');
INSERT INTO country VALUES ('ZM', 'Zambia', 'Africa', 'ZMB', 'Eastern Africa', 'Lusaka', 'EAF');
INSERT INTO country VALUES ('ZW', 'Zimbabwe', 'Africa', 'ZWE', 'Eastern Africa', 'Harare', 'EAF');
INSERT INTO country VALUES ('DZ', 'Algeria', 'Africa', 'DZA', 'Northern Africa', 'Algiers', 'NAF');
INSERT INTO country VALUES ('EG', 'Egypt', 'Africa', 'EGY', 'Northern Africa', 'Cairo', 'NAF');
INSERT INTO country VALUES ('EH', 'Western Sahara', 'Africa', 'ESH', 'Northern Africa', 'El Aaiún', 'NAF');
INSERT INTO country VALUES ('LY', 'Libya', 'Africa', 'LBY', 'Northern Africa', 'Tripoli', 'NAF');
INSERT INTO country VALUES ('MA', 'Morocco', 'Africa', 'MAR', 'Northern Africa', 'Rabat', 'NAF');
INSERT INTO country VALUES ('SD', 'Sudan', 'Africa', 'SDN', 'Northern Africa', 'Khartoum', 'NAF');
INSERT INTO country VALUES ('TN', 'Tunisia', 'Africa', 'TUN', 'Northern Africa', 'Tunis', 'NAF');
INSERT INTO country VALUES ('BW', 'Botswana', 'Africa', 'BWA', 'Southern Africa', 'Gaborone', 'SAF');
INSERT INTO country VALUES ('LS', 'Lesotho', 'Africa', 'LSO', 'Southern Africa', 'Maseru', 'SAF');
INSERT INTO country VALUES ('NA', 'Namibia', 'Africa', 'NAM', 'Southern Africa', 'Windhoek', 'SAF');
INSERT INTO country VALUES ('SZ', 'Swaziland', 'Africa', 'SWZ', 'Southern Africa', 'Lobamba', 'SAF');
INSERT INTO country VALUES ('ZA', 'South Africa', 'Africa', 'ZAF', 'Southern Africa', 'Pretoria', 'SAF');
INSERT INTO country VALUES ('AO', 'Angola', 'Africa', 'AGO', 'Middle Africa', 'Luanda', 'MAF');
INSERT INTO country VALUES ('CF', 'Central African Republic', 'Africa', 'CAF', 'Middle Africa', 'Bangui', 'MAF');
INSERT INTO country VALUES ('CM', 'Cameroon', 'Africa', 'CMR', 'Middle Africa', 'Yaoundé', 'MAF');
INSERT INTO country VALUES ('CD', 'DR Congo', 'Africa', 'COD', 'Middle Africa', 'Kinshasa', 'MAF');
INSERT INTO country VALUES ('CG', 'Republic of the Congo', 'Africa', 'COG', 'Middle Africa', 'Brazzaville', 'MAF');
INSERT INTO country VALUES ('GA', 'Gabon', 'Africa', 'GAB', 'Middle Africa', 'Libreville', 'MAF');
INSERT INTO country VALUES ('GQ', 'Equatorial Guinea', 'Africa', 'GNQ', 'Middle Africa', 'Malabo', 'MAF');
INSERT INTO country VALUES ('SS', 'South Sudan', 'Africa', 'SSD', 'Middle Africa', 'Juba', 'MAF');
INSERT INTO country VALUES ('ST', 'São Tomé and Príncipe', 'Africa', 'STP', 'Middle Africa', 'São Tomé', 'MAF');
INSERT INTO country VALUES ('TD', 'Chad', 'Africa', 'TCD', 'Middle Africa', 'N''Djamena', 'MAF');
INSERT INTO country VALUES ('AF', 'Afghanistan', 'Asia', 'AFG', 'Southern Asia', 'Kabul', 'SA');
INSERT INTO country VALUES ('BD', 'Bangladesh', 'Asia', 'BGD', 'Southern Asia', 'Dhaka', 'SA');
INSERT INTO country VALUES ('BT', 'Bhutan', 'Asia', 'BTN', 'Southern Asia', 'Thimphu', 'SA');
INSERT INTO country VALUES ('IN', 'India', 'Asia', 'IND', 'Southern Asia', 'New Delhi', 'SA');
INSERT INTO country VALUES ('IR', 'Iran', 'Asia', 'IRN', 'Southern Asia', 'Tehran', 'SA');
INSERT INTO country VALUES ('LK', 'Sri Lanka', 'Asia', 'LKA', 'Southern Asia', 'Colombo', 'SA');
INSERT INTO country VALUES ('MV', 'Maldives', 'Asia', 'MDV', 'Southern Asia', 'Malé', 'SA');
INSERT INTO country VALUES ('NP', 'Nepal', 'Asia', 'NPL', 'Southern Asia', 'Kathmandu', 'SA');
INSERT INTO country VALUES ('PK', 'Pakistan', 'Asia', 'PAK', 'Southern Asia', 'Islamabad', 'SA');
INSERT INTO country VALUES ('CN', 'China', 'Asia', 'CHN', 'Eastern Asia', 'Beijing', 'EA');
INSERT INTO country VALUES ('HK', 'Hong Kong', 'Asia', 'HKG', 'Eastern Asia', 'City of Victoria', 'EA');
INSERT INTO country VALUES ('JP', 'Japan', 'Asia', 'JPN', 'Eastern Asia', 'Tokyo', 'EA');
INSERT INTO country VALUES ('KR', 'South Korea', 'Asia', 'KOR', 'Eastern Asia', 'Seoul', 'EA');
INSERT INTO country VALUES ('MO', 'Macau', 'Asia', 'MAC', 'Eastern Asia', '', 'EA');
INSERT INTO country VALUES ('MN', 'Mongolia', 'Asia', 'MNG', 'Eastern Asia', 'Ulan Bator', 'EA');
INSERT INTO country VALUES ('KP', 'North Korea', 'Asia', 'PRK', 'Eastern Asia', 'Pyongyang', 'EA');
INSERT INTO country VALUES ('TW', 'Taiwan', 'Asia', 'TWN', 'Eastern Asia', 'Taipei', 'EA');
INSERT INTO country VALUES ('AE', 'United Arab Emirates', 'Asia', 'ARE', 'Western Asia', 'Abu Dhabi', 'WA');
INSERT INTO country VALUES ('AM', 'Armenia', 'Asia', 'ARM', 'Western Asia', 'Yerevan', 'WA');
INSERT INTO country VALUES ('AZ', 'Azerbaijan', 'Asia', 'AZE', 'Western Asia', 'Baku', 'WA');
INSERT INTO country VALUES ('BH', 'Bahrain', 'Asia', 'BHR', 'Western Asia', 'Manama', 'WA');
INSERT INTO country VALUES ('GE', 'Georgia', 'Asia', 'GEO', 'Western Asia', 'Tbilisi', 'WA');
INSERT INTO country VALUES ('IQ', 'Iraq', 'Asia', 'IRQ', 'Western Asia', 'Baghdad', 'WA');
INSERT INTO country VALUES ('IL', 'Israel', 'Asia', 'ISR', 'Western Asia', 'Jerusalem', 'WA');
INSERT INTO country VALUES ('JO', 'Jordan', 'Asia', 'JOR', 'Western Asia', 'Amman', 'WA');
INSERT INTO country VALUES ('KW', 'Kuwait', 'Asia', 'KWT', 'Western Asia', 'Kuwait City', 'WA');
INSERT INTO country VALUES ('LB', 'Lebanon', 'Asia', 'LBN', 'Western Asia', 'Beirut', 'WA');
INSERT INTO country VALUES ('OM', 'Oman', 'Asia', 'OMN', 'Western Asia', 'Muscat', 'WA');
INSERT INTO country VALUES ('PS', 'Palestine', 'Asia', 'PSE', 'Western Asia', 'Ramallah', 'WA');
INSERT INTO country VALUES ('QA', 'Qatar', 'Asia', 'QAT', 'Western Asia', 'Doha', 'WA');
INSERT INTO country VALUES ('SA', 'Saudi Arabia', 'Asia', 'SAU', 'Western Asia', 'Riyadh', 'WA');
INSERT INTO country VALUES ('SY', 'Syria', 'Asia', 'SYR', 'Western Asia', 'Damascus', 'WA');
INSERT INTO country VALUES ('TR', 'Turkey', 'Asia', 'TUR', 'Western Asia', 'Ankara', 'WA');
INSERT INTO country VALUES ('YE', 'Yemen', 'Asia', 'YEM', 'Western Asia', 'Sana''a', 'WA');
INSERT INTO country VALUES ('KZ', 'Kazakhstan', 'Asia', 'KAZ', 'Central Asia', 'Astana', 'CA');
INSERT INTO country VALUES ('KG', 'Kyrgyzstan', 'Asia', 'KGZ', 'Central Asia', 'Bishkek', 'CA');
INSERT INTO country VALUES ('TJ', 'Tajikistan', 'Asia', 'TJK', 'Central Asia', 'Dushanbe', 'CA');
INSERT INTO country VALUES ('TM', 'Turkmenistan', 'Asia', 'TKM', 'Central Asia', 'Ashgabat', 'CA');
INSERT INTO country VALUES ('UZ', 'Uzbekistan', 'Asia', 'UZB', 'Central Asia', 'Tashkent', 'CA');
INSERT INTO country VALUES ('BN', 'Brunei', 'Asia', 'BRN', 'South-Eastern Asia', 'Bandar Seri Begawan', 'SEA');
INSERT INTO country VALUES ('ID', 'Indonesia', 'Asia', 'IDN', 'South-Eastern Asia', 'Jakarta', 'SEA');
INSERT INTO country VALUES ('KH', 'Cambodia', 'Asia', 'KHM', 'South-Eastern Asia', 'Phnom Penh', 'SEA');
INSERT INTO country VALUES ('LA', 'Laos', 'Asia', 'LAO', 'South-Eastern Asia', 'Vientiane', 'SEA');
INSERT INTO country VALUES ('MM', 'Myanmar', 'Asia', 'MMR', 'South-Eastern Asia', 'Naypyidaw', 'SEA');
INSERT INTO country VALUES ('MY', 'Malaysia', 'Asia', 'MYS', 'South-Eastern Asia', 'Kuala Lumpur', 'SEA');
INSERT INTO country VALUES ('PH', 'Philippines', 'Asia', 'PHL', 'South-Eastern Asia', 'Manila', 'SEA');
INSERT INTO country VALUES ('SG', 'Singapore', 'Asia', 'SGP', 'South-Eastern Asia', 'Singapore', 'SEA');
INSERT INTO country VALUES ('TH', 'Thailand', 'Asia', 'THA', 'South-Eastern Asia', 'Bangkok', 'SEA');
INSERT INTO country VALUES ('TL', 'Timor-Leste', 'Asia', 'TLS', 'South-Eastern Asia', 'Dili', 'SEA');
INSERT INTO country VALUES ('VN', 'Vietnam', 'Asia', 'VNM', 'South-Eastern Asia', 'Hanoi', 'SEA');
INSERT INTO country VALUES ('AX', 'Åland Islands', 'Europe', 'ALA', 'Northern Europe', 'Mariehamn', 'NE');
INSERT INTO country VALUES ('DK', 'Denmark', 'Europe', 'DNK', 'Northern Europe', 'Copenhagen', 'NE');
INSERT INTO country VALUES ('EE', 'Estonia', 'Europe', 'EST', 'Northern Europe', 'Tallinn', 'NE');
INSERT INTO country VALUES ('FI', 'Finland', 'Europe', 'FIN', 'Northern Europe', 'Helsinki', 'NE');
INSERT INTO country VALUES ('FO', 'Faroe Islands', 'Europe', 'FRO', 'Northern Europe', 'Tórshavn', 'NE');
INSERT INTO country VALUES ('GB', 'United Kingdom', 'Europe', 'GBR', 'Northern Europe', 'London', 'NE');
INSERT INTO country VALUES ('GG', 'Guernsey', 'Europe', 'GGY', 'Northern Europe', 'St. Peter Port', 'NE');
INSERT INTO country VALUES ('IM', 'Isle of Man', 'Europe', 'IMN', 'Northern Europe', 'Douglas', 'NE');
INSERT INTO country VALUES ('IE', 'Ireland', 'Europe', 'IRL', 'Northern Europe', 'Dublin', 'NE');
INSERT INTO country VALUES ('IS', 'Iceland', 'Europe', 'ISL', 'Northern Europe', 'Reykjavik', 'NE');
INSERT INTO country VALUES ('JE', 'Jersey', 'Europe', 'JEY', 'Northern Europe', 'Saint Helier', 'NE');
INSERT INTO country VALUES ('LT', 'Lithuania', 'Europe', 'LTU', 'Northern Europe', 'Vilnius', 'NE');
INSERT INTO country VALUES ('LV', 'Latvia', 'Europe', 'LVA', 'Northern Europe', 'Riga', 'NE');
INSERT INTO country VALUES ('NO', 'Norway', 'Europe', 'NOR', 'Northern Europe', 'Oslo', 'NE');
INSERT INTO country VALUES ('SJ', 'Svalbard and Jan Mayen', 'Europe', 'SJM', 'Northern Europe', 'Longyearbyen', 'NE');
INSERT INTO country VALUES ('SE', 'Sweden', 'Europe', 'SWE', 'Northern Europe', 'Stockholm', 'NE');
INSERT INTO country VALUES ('AL', 'Albania', 'Europe', 'ALB', 'Southern Europe', 'Tirana', 'SE');
INSERT INTO country VALUES ('AD', 'Andorra', 'Europe', 'AND', 'Southern Europe', 'Andorra la Vella', 'SE');
INSERT INTO country VALUES ('BA', 'Bosnia and Herzegovina', 'Europe', 'BIH', 'Southern Europe', 'Sarajevo', 'SE');
INSERT INTO country VALUES ('ES', 'Spain', 'Europe', 'ESP', 'Southern Europe', 'Madrid', 'SE');
INSERT INTO country VALUES ('GI', 'Gibraltar', 'Europe', 'GIB', 'Southern Europe', 'Gibraltar', 'SE');
INSERT INTO country VALUES ('GR', 'Greece', 'Europe', 'GRC', 'Southern Europe', 'Athens', 'SE');
INSERT INTO country VALUES ('HR', 'Croatia', 'Europe', 'HRV', 'Southern Europe', 'Zagreb', 'SE');
INSERT INTO country VALUES ('IT', 'Italy', 'Europe', 'ITA', 'Southern Europe', 'Rome', 'SE');
INSERT INTO country VALUES ('MK', 'Macedonia', 'Europe', 'MKD', 'Southern Europe', 'Skopje', 'SE');
INSERT INTO country VALUES ('MT', 'Malta', 'Europe', 'MLT', 'Southern Europe', 'Valletta', 'SE');
INSERT INTO country VALUES ('ME', 'Montenegro', 'Europe', 'MNE', 'Southern Europe', 'Podgorica', 'SE');
INSERT INTO country VALUES ('PT', 'Portugal', 'Europe', 'PRT', 'Southern Europe', 'Lisbon', 'SE');
INSERT INTO country VALUES ('SM', 'San Marino', 'Europe', 'SMR', 'Southern Europe', 'City of San Marino', 'SE');
INSERT INTO country VALUES ('RS', 'Serbia', 'Europe', 'SRB', 'Southern Europe', 'Belgrade', 'SE');
INSERT INTO country VALUES ('SI', 'Slovenia', 'Europe', 'SVN', 'Southern Europe', 'Ljubljana', 'SE');
INSERT INTO country VALUES ('VA', 'Vatican City', 'Europe', 'VAT', 'Southern Europe', 'Vatican City', 'SE');
INSERT INTO country VALUES ('AT', 'Austria', 'Europe', 'AUT', 'Western Europe', 'Vienna', 'WE');
INSERT INTO country VALUES ('BE', 'Belgium', 'Europe', 'BEL', 'Western Europe', 'Brussels', 'WE');
INSERT INTO country VALUES ('CH', 'Switzerland', 'Europe', 'CHE', 'Western Europe', 'Bern', 'WE');
INSERT INTO country VALUES ('DE', 'Germany', 'Europe', 'DEU', 'Western Europe', 'Berlin', 'WE');
INSERT INTO country VALUES ('FR', 'France', 'Europe', 'FRA', 'Western Europe', 'Paris', 'WE');
INSERT INTO country VALUES ('LI', 'Liechtenstein', 'Europe', 'LIE', 'Western Europe', 'Vaduz', 'WE');
INSERT INTO country VALUES ('LU', 'Luxembourg', 'Europe', 'LUX', 'Western Europe', 'Luxembourg', 'WE');
INSERT INTO country VALUES ('MC', 'Monaco', 'Europe', 'MCO', 'Western Europe', 'Monaco', 'WE');
INSERT INTO country VALUES ('NL', 'Netherlands', 'Europe', 'NLD', 'Western Europe', 'Amsterdam', 'WE');
INSERT INTO country VALUES ('BG', 'Bulgaria', 'Europe', 'BGR', 'Eastern Europe', 'Sofia', 'EE');
INSERT INTO country VALUES ('BY', 'Belarus', 'Europe', 'BLR', 'Eastern Europe', 'Minsk', 'EE');
INSERT INTO country VALUES ('CY', 'Cyprus', 'Europe', 'CYP', 'Eastern Europe', 'Nicosia', 'EE');
INSERT INTO country VALUES ('CZ', 'Czech Republic', 'Europe', 'CZE', 'Eastern Europe', 'Prague', 'EE');
INSERT INTO country VALUES ('HU', 'Hungary', 'Europe', 'HUN', 'Eastern Europe', 'Budapest', 'EE');
INSERT INTO country VALUES ('XK', 'Kosovo', 'Europe', 'UNK', 'Eastern Europe', 'Pristina', 'EE');
INSERT INTO country VALUES ('MD', 'Moldova', 'Europe', 'MDA', 'Eastern Europe', 'Chișinău', 'EE');
INSERT INTO country VALUES ('PL', 'Poland', 'Europe', 'POL', 'Eastern Europe', 'Warsaw', 'EE');
INSERT INTO country VALUES ('RO', 'Romania', 'Europe', 'ROU', 'Eastern Europe', 'Bucharest', 'EE');
INSERT INTO country VALUES ('RU', 'Russia', 'Europe', 'RUS', 'Eastern Europe', 'Moscow', 'EE');
INSERT INTO country VALUES ('SK', 'Slovakia', 'Europe', 'SVK', 'Eastern Europe', 'Bratislava', 'EE');
INSERT INTO country VALUES ('UA', 'Ukraine', 'Europe', 'UKR', 'Eastern Europe', 'Kiev', 'EE');
INSERT INTO country VALUES ('BM', 'Bermuda', 'Americas', 'BMU', 'Northern America', 'Hamilton', 'NAM');
INSERT INTO country VALUES ('CA', 'Canada', 'Americas', 'CAN', 'Northern America', 'Ottawa', 'NAM');
INSERT INTO country VALUES ('GL', 'Greenland', 'Americas', 'GRL', 'Northern America', 'Nuuk', 'NAM');
INSERT INTO country VALUES ('PM', 'Saint Pierre and Miquelon', 'Americas', 'SPM', 'Northern America', 'Saint-Pierre', 'NAM');
INSERT INTO country VALUES ('UM', 'United States Minor Outlying Islands', 'Americas', 'UMI', 'Northern America', '', 'NAM');
INSERT INTO country VALUES ('US', 'United States', 'Americas', 'USA', 'Northern America', 'Washington D.C.', 'NAM');
INSERT INTO country VALUES ('AR', 'Argentina', 'Americas', 'ARG', 'South America', 'Buenos Aires', 'SAM');
INSERT INTO country VALUES ('BO', 'Bolivia', 'Americas', 'BOL', 'South America', 'Sucre', 'SAM');
INSERT INTO country VALUES ('BR', 'Brazil', 'Americas', 'BRA', 'South America', 'Brasília', 'SAM');
INSERT INTO country VALUES ('CL', 'Chile', 'Americas', 'CHL', 'South America', 'Santiago', 'SAM');
INSERT INTO country VALUES ('CO', 'Colombia', 'Americas', 'COL', 'South America', 'Bogotá', 'SAM');
INSERT INTO country VALUES ('EC', 'Ecuador', 'Americas', 'ECU', 'South America', 'Quito', 'SAM');
INSERT INTO country VALUES ('FK', 'Falkland Islands', 'Americas', 'FLK', 'South America', 'Stanley', 'SAM');
INSERT INTO country VALUES ('GF', 'French Guiana', 'Americas', 'GUF', 'South America', 'Cayenne', 'SAM');
INSERT INTO country VALUES ('GY', 'Guyana', 'Americas', 'GUY', 'South America', 'Georgetown', 'SAM');
INSERT INTO country VALUES ('PE', 'Peru', 'Americas', 'PER', 'South America', 'Lima', 'SAM');
INSERT INTO country VALUES ('PY', 'Paraguay', 'Americas', 'PRY', 'South America', 'Asunción', 'SAM');
INSERT INTO country VALUES ('GS', 'South Georgia', 'Americas', 'SGS', 'South America', 'King Edward Point', 'SAM');
INSERT INTO country VALUES ('SR', 'Suriname', 'Americas', 'SUR', 'South America', 'Paramaribo', 'SAM');
INSERT INTO country VALUES ('UY', 'Uruguay', 'Americas', 'URY', 'South America', 'Montevideo', 'SAM');
INSERT INTO country VALUES ('VE', 'Venezuela', 'Americas', 'VEN', 'South America', 'Caracas', 'SAM');
INSERT INTO country VALUES ('BZ', 'Belize', 'Americas', 'BLZ', 'Central America', 'Belmopan', 'CAM');
INSERT INTO country VALUES ('CR', 'Costa Rica', 'Americas', 'CRI', 'Central America', 'San José', 'CAM');
INSERT INTO country VALUES ('GT', 'Guatemala', 'Americas', 'GTM', 'Central America', 'Guatemala City', 'CAM');
INSERT INTO country VALUES ('HN', 'Honduras', 'Americas', 'HND', 'Central America', 'Tegucigalpa', 'CAM');
INSERT INTO country VALUES ('MX', 'Mexico', 'Americas', 'MEX', 'Central America', 'Mexico City', 'CAM');
INSERT INTO country VALUES ('NI', 'Nicaragua', 'Americas', 'NIC', 'Central America', 'Managua', 'CAM');
INSERT INTO country VALUES ('PA', 'Panama', 'Americas', 'PAN', 'Central America', 'Panama City', 'CAM');
INSERT INTO country VALUES ('SV', 'El Salvador', 'Americas', 'SLV', 'Central America', 'San Salvador', 'CAM');
INSERT INTO country VALUES ('AW', 'Aruba', 'Americas', 'ABW', 'Caribbean', 'Oranjestad', 'CAR');
INSERT INTO country VALUES ('AI', 'Anguilla', 'Americas', 'AIA', 'Caribbean', 'The Valley', 'CAR');
INSERT INTO country VALUES ('AG', 'Antigua and Barbuda', 'Americas', 'ATG', 'Caribbean', 'Saint John''s', 'CAR');
INSERT INTO country VALUES ('BS', 'Bahamas', 'Americas', 'BHS', 'Caribbean', 'Nassau', 'CAR');
INSERT INTO country VALUES ('BL', 'Saint Barthélemy', 'Americas', 'BLM', 'Caribbean', 'Gustavia', 'CAR');
INSERT INTO country VALUES ('BB', 'Barbados', 'Americas', 'BRB', 'Caribbean', 'Bridgetown', 'CAR');
INSERT INTO country VALUES ('CU', 'Cuba', 'Americas', 'CUB', 'Caribbean', 'Havana', 'CAR');
INSERT INTO country VALUES ('CW', 'Curaçao', 'Americas', 'CUW', 'Caribbean', 'Willemstad', 'CAR');
INSERT INTO country VALUES ('KY', 'Cayman Islands', 'Americas', 'CYM', 'Caribbean', 'George Town', 'CAR');
INSERT INTO country VALUES ('DM', 'Dominica', 'Americas', 'DMA', 'Caribbean', 'Roseau', 'CAR');
INSERT INTO country VALUES ('DO', 'Dominican Republic', 'Americas', 'DOM', 'Caribbean', 'Santo Domingo', 'CAR');
INSERT INTO country VALUES ('GP', 'Guadeloupe', 'Americas', 'GLP', 'Caribbean', 'Basse-Terre', 'CAR');
INSERT INTO country VALUES ('GD', 'Grenada', 'Americas', 'GRD', 'Caribbean', 'St. George''s', 'CAR');
INSERT INTO country VALUES ('HT', 'Haiti', 'Americas', 'HTI', 'Caribbean', 'Port-au-Prince', 'CAR');
INSERT INTO country VALUES ('JM', 'Jamaica', 'Americas', 'JAM', 'Caribbean', 'Kingston', 'CAR');
INSERT INTO country VALUES ('KN', 'Saint Kitts and Nevis', 'Americas', 'KNA', 'Caribbean', 'Basseterre', 'CAR');
INSERT INTO country VALUES ('LC', 'Saint Lucia', 'Americas', 'LCA', 'Caribbean', 'Castries', 'CAR');
INSERT INTO country VALUES ('MF', 'Saint Martin', 'Americas', 'MAF', 'Caribbean', 'Marigot', 'CAR');
INSERT INTO country VALUES ('MS', 'Montserrat', 'Americas', 'MSR', 'Caribbean', 'Plymouth', 'CAR');
INSERT INTO country VALUES ('MQ', 'Martinique', 'Americas', 'MTQ', 'Caribbean', 'Fort-de-France', 'CAR');
INSERT INTO country VALUES ('PR', 'Puerto Rico', 'Americas', 'PRI', 'Caribbean', 'San Juan', 'CAR');
INSERT INTO country VALUES ('SX', 'Sint Maarten', 'Americas', 'SXM', 'Caribbean', 'Philipsburg', 'CAR');
INSERT INTO country VALUES ('TC', 'Turks and Caicos Islands', 'Americas', 'TCA', 'Caribbean', 'Cockburn Town', 'CAR');
INSERT INTO country VALUES ('TT', 'Trinidad and Tobago', 'Americas', 'TTO', 'Caribbean', 'Port of Spain', 'CAR');
INSERT INTO country VALUES ('VC', 'Saint Vincent and the Grenadines', 'Americas', 'VCT', 'Caribbean', 'Kingstown', 'CAR');
INSERT INTO country VALUES ('VG', 'British Virgin Islands', 'Americas', 'VGB', 'Caribbean', 'Road Town', 'CAR');
INSERT INTO country VALUES ('VI', 'United States Virgin Islands', 'Americas', 'VIR', 'Caribbean', 'Charlotte Amalie', 'CAR');
INSERT INTO country VALUES ('NC', 'New Caledonia', 'Oceania', 'NCL', 'Melanesia', 'Nouméa', 'MEL');
INSERT INTO country VALUES ('PG', 'Papua New Guinea', 'Oceania', 'PNG', 'Melanesia', 'Port Moresby', 'MEL');
INSERT INTO country VALUES ('SB', 'Solomon Islands', 'Oceania', 'SLB', 'Melanesia', 'Honiara', 'MEL');
INSERT INTO country VALUES ('VU', 'Vanuatu', 'Oceania', 'VUT', 'Melanesia', 'Port Vila', 'MEL');
INSERT INTO country VALUES ('FM', 'Micronesia', 'Oceania', 'FSM', 'Micronesia', 'Palikir', 'MIC');
INSERT INTO country VALUES ('GU', 'Guam', 'Oceania', 'GUM', 'Micronesia', 'Hagåtña', 'MIC');
INSERT INTO country VALUES ('KI', 'Kiribati', 'Oceania', 'KIR', 'Micronesia', 'South Tarawa', 'MIC');
INSERT INTO country VALUES ('MH', 'Marshall Islands', 'Oceania', 'MHL', 'Micronesia', 'Majuro', 'MIC');
INSERT INTO country VALUES ('MP', 'Northern Mariana Islands', 'Oceania', 'MNP', 'Micronesia', 'Saipan', 'MIC');
INSERT INTO country VALUES ('NR', 'Nauru', 'Oceania', 'NRU', 'Micronesia', 'Yaren', 'MIC');
INSERT INTO country VALUES ('PW', 'Palau', 'Oceania', 'PLW', 'Micronesia', 'Ngerulmud', 'MIC');
INSERT INTO country VALUES ('AS', 'American Samoa', 'Oceania', 'ASM', 'Polynesia', 'Pago Pago', 'POL');
INSERT INTO country VALUES ('CK', 'Cook Islands', 'Oceania', 'COK', 'Polynesia', 'Avarua', 'POL');
INSERT INTO country VALUES ('NU', 'Niue', 'Oceania', 'NIU', 'Polynesia', 'Alofi', 'POL');
INSERT INTO country VALUES ('PN', 'Pitcairn Islands', 'Oceania', 'PCN', 'Polynesia', 'Adamstown', 'POL');
INSERT INTO country VALUES ('PF', 'French Polynesia', 'Oceania', 'PYF', 'Polynesia', 'Papeetē', 'POL');
INSERT INTO country VALUES ('TK', 'Tokelau', 'Oceania', 'TKL', 'Polynesia', 'Fakaofo', 'POL');
INSERT INTO country VALUES ('TO', 'Tonga', 'Oceania', 'TON', 'Polynesia', 'Nuku''alofa', 'POL');
INSERT INTO country VALUES ('TV', 'Tuvalu', 'Oceania', 'TUV', 'Polynesia', 'Funafuti', 'POL');
INSERT INTO country VALUES ('WF', 'Wallis and Futuna', 'Oceania', 'WLF', 'Polynesia', 'Mata-Utu', 'POL');
INSERT INTO country VALUES ('WS', 'Samoa', 'Oceania', 'WSM', 'Polynesia', 'Apia', 'POL');
INSERT INTO country VALUES ('AQ', 'Antarctica', 'Antarctica', 'ATA', 'Antarctica', '', 'ANT');
INSERT INTO country VALUES ('TF', 'French Southern and Antarctic Lands', 'Antarctica', 'ATF', 'Antarctica', 'Port-aux-Français', 'ANT');
INSERT INTO country VALUES ('BV', 'Bouvet Island', 'Antarctica', 'BVT', 'Antarctica', '', 'ANT');
INSERT INTO country VALUES ('HM', 'Heard Island and McDonald Islands', 'Antarctica', 'HMD', 'Antarctica', '', 'ANT');
INSERT INTO country VALUES ('AU', 'Australia', 'Oceania', 'AUS', 'Australia and New Zealand', 'Canberra', 'AUS');
INSERT INTO country VALUES ('CC', 'Cocos (Keeling) Islands', 'Oceania', 'CCK', 'Australia and New Zealand', 'West Island', 'AUS');
INSERT INTO country VALUES ('CX', 'Christmas Island', 'Oceania', 'CXR', 'Australia and New Zealand', 'Flying Fish Cove', 'AUS');
INSERT INTO country VALUES ('NF', 'Norfolk Island', 'Oceania', 'NFK', 'Australia and New Zealand', 'Kingston', 'AUS');
INSERT INTO country VALUES ('NZ', 'New Zealand', 'Oceania', 'NZL', 'Australia and New Zealand', 'Wellington', 'AUS');


-- Completed on 2016-03-20 19:59:18

--
-- PostgreSQL database dump complete
--
