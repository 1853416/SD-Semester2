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
hour = document.getElementById('hour');
const phone = localStorage.getItem('Phone');
const usersCollection = database.collection('Doctor'); 

const Toast={
  init(){
    
    this.el=document.createElement('div');
    this.el.className='toast';
    document.body.appendChild(this.el);
  },
  show(message,state){
    this.hideTimeout=null;

    //clearTimeout(this,hideTimeout);

    this.el.textContent=message;
    this.el.className='toast toast--visible';
    if(state){
      this.el.classList.add(`toast--${state}`);
    }
    this.hideTimeout=setTimeout(()=>{
      this.el.classList.remove('toast--visible');

    },4000);

  }
};
document.addEventListener('DOMContentLoaded',()=>Toast.init());

window.addEventListener('load', () => {

  // Via local Storage
  const phone = localStorage.getItem('Phone');
  
  
  const phoneCheck = database.collection('Doctor').doc(phone);
  phoneCheck.get()
  .then((docSnapshot) => {
    if (docSnapshot.exists) {
      phoneCheck.onSnapshot((doc) => {       
        doctorData=docSnapshot.data();
        console.log(doctorData['LastName']);
        document.getElementById("DrNamehead").textContent = 'Dr '+ doctorData['LastName'];
      });
    } else {
      alert("not found")// create the document
    }
  });


})

btnPost.addEventListener('click', e => {
  

  if(document.getElementById('hour').checked) {
     hour = document.getElementById('hour');
   
  }else if(document.getElementById('halfhour').checked) {
     hour = document.getElementById('halfhour');
    
  }
 

    const ID = usersCollection.doc(phone).update({
        "Start Date": StartDate.value,
        "End Date": EndDate.value,
        "Start Time": startTime.value,
        "End Time": endTime.value,
        "Hour" :hour.value,
        
         
    
      })
      .then(()=>{
        //alert('Info saved!!')
        Toast.show('Data has been saved successfully !','success')
        console.log('Data has been saved successfully !')})
      .catch(error => {
        console.error(error)
      });

  });

