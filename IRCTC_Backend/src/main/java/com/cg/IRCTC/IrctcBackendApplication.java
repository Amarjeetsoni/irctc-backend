package com.cg.IRCTC;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IrctcBackendApplication {

	public static void main(String[] args) {
//		ClassLoader classLoader = IrctcBackendApplication.class.getClassLoader();
//		File file = new File(Objects.requireNonNull(classLoader.getResource("serviceAccountKey.json")).getFile());
//		FileInputStream serviceAccount = new FileInputStream(file.getAbsolutePath());
//
////		FileInputStream serviceAccount =
////				new FileInputStream("path/to/serviceAccountKey.json");
//
//		FirebaseOptions options = new FirebaseOptions.Builder()
//  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//  .build();
//
//		FirebaseApp.initializeApp(options);

		
		SpringApplication.run(IrctcBackendApplication.class, args);
	}

}
