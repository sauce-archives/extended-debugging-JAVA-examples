test:
	make clean_it test_parallel

clean_it:
	mvn clean

test_parallel:
	make -j windows_10 windows_8 mac_sierra

windows_10:
	mvn install -Dplatform=windows_10

windows_8:
	mvn install -Dplatform=windows_8

mac_sierra:
	mvn install -Dplatform=mac_sierra
