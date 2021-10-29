var firebaseConfig = {
    apiKey: "AIzaSyABC45epEv116XDWIa-Oag1g9jzjyLDBrY",
    authDomain: "mpt-web-tester.firebaseapp.com",
    projectId: "mpt-web-tester",
    storageBucket: "mpt-web-tester.appspot.com",
    messagingSenderId: "818343226778",
    appId: "1:818343226778:web:67be5a6916ab3b02c71e23"
    
  };
    // Initialize Firebase
    firebase.initializeApp(firebaseConfig);
    // Initialize variables
    const auth = firebase.auth();
    const database = firebase.firestore();
    const pname = document.getElementById('pname');
    const dateOfVisit = document.getElementById('dateOfVisit');
    const appNotes = document.getElementById('appNotes');
    const script = document.getElementById('script');
    // const dprel = document.getElementById('dprel');
    const btnSave = document.getElementById('btnSave');

    
const usersCollection = database.collection('Doctor'); 

btnSave.addEventListener('click', e => {
    const ID = usersCollection.doc(phone).collection('Scripts').add({
        "Patient Name": pname.value,
        "Date of visit": dateOfVisit.value,
        "App Notes": appNotes.value,
        "Perscription": script.value,
        
      })
      .then(()=>{
        alert('Info saved!!')
        console.log('Data has been saved successfully !')})
      .catch(error => {
        console.error(error)
      });

  });

  window.addEventListener('load', () => {

    // Via local Storage
    const phone = localStorage.getItem('Phone');
    
   


})