grammar Pandemic;

actions
	: ( action ';' )*  EOF
	; 

action 
	: joinAction
	| buildAction
	| directFlightAction
	| charterFlightAction
	| discoverAction
	| quitAction
	| ferryAction
	| operationFlightAction
	| treatAction
	;

buildAction : 'build a research station' ( 'by discard' city ) ? ;

directFlightAction : ( 'discard for' ) ? 'direct flight to' city ;

charterFlightAction : ( 'discard for' ) ? 'charter flight from' city ;

discoverAction : 'discover a cure by discard' city ',' city ',' city ',' city ( ',' city ) ? ;

quitAction : 'quit' ;

ferryAction : 'ferry to' city ;

operationFlightAction : 'discard' city 'for operation flight to' city ;

joinAction : 'players' player ',' player ( ',' player ( ',' player ( ',' player ) ? ) ? ) ? 'join' ;

treatAction : 'treat a disease' ;

player : Identifier 'the' role ;

city
	: 'Atlanta' 
	| 'Washington'
	| 'Chicago'
	| 'Montreal'
	| 'New York'
	| 'San Francisco'
	| 'London'
	| 'Essen'
	| 'Paris'
	| 'Madrid'
	| 'Milan'
	| 'St. Petersburg'
	| 'Los Angeles'
	| 'Mexico City'
	| 'Miami'
	| 'Bogota'
	| 'Lima'
	| 'Santiago'
	| 'Buenos Aires'
	| 'Sao Paulo'
	| 'Lagos'
	| 'Kinshasa'
	| 'Khartoum'
	| 'Johannesburg'
	| 'Algiers'
	| 'Cairo'
	| 'Istanbul'
	| 'Moscow'
	| 'Baghdad'
	| 'Tehran'
	| 'Riyadh'
	| 'Karachi'
	| 'Delhi'
	| 'Kolkata'
	| 'Mumbai'
	| 'Chennai'
	| 'Jakarta'
	| 'Ho Chi Minh City'
	| 'Bangkok'
	| 'Hong Kong'
	| 'Manila'
	| 'Taipei'
	| 'Osaka'
	| 'Shanghai'
	| 'Beijing'
	| 'Seoul'
	| 'Tokyo'
	| 'Sydney'
	;

role
	: 'Medic'
	| 'Operations Expert'
	| 'Dispatcher'
	| 'Scientist'
	| 'Researcher'
	| 'Contingency Planner'
	| 'Quarantine Specialist'
	| 'Field Operative'
	| 'Generalist'
	| 'Containment Specialist'
	| 'Epidemiologist'
	| 'Archivist'
	| 'Troubleshooter'
	;
// Identifier
Identifier : LetterOrDigit+ ;

fragment LetterOrDigit : [a-zA-Z0-9] ;

// Whitespace
WS  :  [ \t\r\n\u000C]+ -> skip
    ;