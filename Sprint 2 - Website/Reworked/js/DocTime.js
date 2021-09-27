var firebaseConfig = {
    apiKey: "AIzaSyABC45epEv116XDWIa-Oag1g9jzjyLDBrY",
  authDomain: "mpt-web-tester.firebaseapp.com",
  projectId: "mpt-web-tester",
  storageBucket: "mpt-web-tester.appspot.com",
  messagingSenderId: "818343226778",
  appId: "1:818343226778:web:67be5a6916ab3b02c71e23"
  };

  firebase.initializeApp(firebaseConfig);
// Initialize variables
const auth = firebase.auth();
const database = firebase.firestore();
const startTime = document.getElementById('StartTime');
const endTime = document.getElementById('EndTime');
const StartDate = document.getElementById('startDate');
const EndDate = document.getElementById('endDate');
const hour = document.getElementById('hour');
const halfhour = document.getElementById('halfhour');

const usersCollection = database.collection('Doctor'); 

btnPost.addEventListener('click', e => {
  const d = Date.now();
  console.log(d)

    const ID = usersCollection.doc('0000000000').collection('Dates').add({
        "Start Date": StartDate.value,
        "End Date": EndDate.value,
        "Start Time": startTime.value,
        "End Time": endTime.value,
        "Hour" :hour.value,
        "HalfHour" :halfhour.value,
        "times" : Date.now()  
    
      })
      .then(()=>{
        alert('Info saved!!')
        console.log('Data has been saved successfully !')})
      .catch(error => {
        console.error(error)
      });

  });