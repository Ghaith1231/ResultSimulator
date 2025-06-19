// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
import { getAnalytics } from "firebase/analytics";
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
  apiKey: "AIzaSyCA2Su8ogbvJ-AmvwMwYxZObIWxHXdjSRk",
  authDomain: "com50003-grade-calculator.firebaseapp.com",
  projectId: "com50003-grade-calculator",
  storageBucket: "com50003-grade-calculator.firebasestorage.app",
  messagingSenderId: "594875753890",
  appId: "1:594875753890:web:6d6963f93a2a47cb927efb",
  measurementId: "G-EHXF0DL7J1"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
const analytics = getAnalytics(app);

