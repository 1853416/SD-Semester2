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
var doctorData= {};
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

async function pullBook(){	

//localStorage.setItem('docName', "0000000000");
var docPhone = localStorage.getItem('Phone');

const db = await database.collection('Bookings').where("doctor_documentID", "==", localStorage.getItem('Phone'));
db.get().then((querySnapshot) => {
  
      querySnapshot.forEach((doc) => {
        console.log(doc.ref.id);
        
          // doc.data() is never undefined for query doc snapshots
          //console.log("Dr" ,doc.data());
    var x = doc.data();
    
    var cards = document.createElement("div");
    const currentDiv = document.getElementById("div1");
    var brk = document.createElement("br");
    cards.setAttribute("class","card")
        const db =  database.collection('Patient').doc(x['patient_documentID']);
        db.get().then((docSnapshot) => {
          if (docSnapshot.exists)
          {
            
            db.onSnapshot((doc2) => {
              if (doc.data()['Status'] == "confirmed")
              {
                var patname = document.createElement("h3");
                patname.innerHTML = ("Patient Name:");
                cards.appendChild(patname);
                var nom = document.createElement("h3");
                nom.innerHTML = doc2.data()['FirstName'] +" "+ doc2.data()['LastName'];
                cards.appendChild(nom);
                
                var dat = document.createElement("p");
                dat.setAttribute("class", "p1")
                dat.innerHTML = x['dateOfVisit'];
                cards.appendChild(dat);
                var tim = document.createElement("p");
                tim.setAttribute("class", "p2")
                tim.innerHTML = x['time'];
                cards.appendChild(tim);
                var hrk = document.createElement("hr");
                cards.appendChild(brk);
                cards.appendChild(hrk);
                var cont = document.createElement("h4");
                cont.innerHTML = ("Patient Contact Details:");
                cards.appendChild(cont);
                var cellNum = document.createElement("p");
                cellNum.setAttribute("class", "p4");
                cellNum.innerHTML = doc2.id;
                cards.appendChild(cellNum);
                var mail = document.createElement("p");
                mail.setAttribute("class", "p4");
                mail.innerHTML = doc2.data()['Email'];
                cards.appendChild(mail);
                var mind = document.createElement("a");
                mind.innerHTML = "Create Appointment Form";
                mind.href = "#";
                mind.id= doc.id;
                mind.setAttribute("onclick", "appform(this);");
                cards.appendChild(mind);
                cards.appendChild(brk);
                var lin = document.createElement("a");
                lin.innerHTML = "Cancel";
                lin.href = "#";
                lin.id= doc.id;
                lin.setAttribute("onclick", "cancel(this);");
                cards.appendChild(lin);
                //console.log(doc.id);
                //console.log("heya");
              }
              else if (doc.data()['Status'] == "missed")
              {
                var patname = document.createElement("h3");
                patname.innerHTML = ("Patient's Name:");
                cards.appendChild(patname);
                var nom = document.createElement("p1");
                nom.innerHTML = doc2.data()['FirstName'] +" "+ doc2.data()['LastName'];
                nom.style.textDecoration = 'line-through';
                cards.appendChild(nom);
                
                var dat = document.createElement("p");
                dat.setAttribute("class", "p1")
                dat.innerHTML = x['dateOfVisit'];
                dat.style.textDecoration = 'line-through';
                cards.appendChild(dat);
                var tim = document.createElement("p");
                tim.setAttribute("class", "p2")
                tim.innerHTML = x['time'];
                tim.style.textDecoration = 'line-through';
                cards.appendChild(tim);
                cards.appendChild(brk);
                var mail = document.createElement("p");
                mail.innerHTML = "Missed";
                cards.appendChild(mail);
              }
              else
              {
                var patname = document.createElement("h3");
                patname.innerHTML = ("Patient's Name:");
                cards.appendChild(patname);
                var nom = document.createElement("p");
                nom.innerHTML = doc2.data()['FirstName'] +" "+ doc2.data()['LastName'];
                nom.style.textDecoration = 'line-through';
                cards.appendChild(nom);
                
                var dat = document.createElement("p");
                dat.setAttribute("class", "p1")
                dat.innerHTML = x['dateOfVisit'];
                dat.style.textDecoration = 'line-through';
                cards.appendChild(dat);
                var tim = document.createElement("p");
                tim.setAttribute("class", "p2")
                tim.innerHTML = x['time'];
                tim.style.textDecoration = 'line-through';
                cards.appendChild(tim);
                cards.appendChild(brk);
                var mail = document.createElement("p");
                mail.innerHTML = "Cancelled";
                cards.appendChild(mail);
              }

              });
            
          }
          else{Toast.show('Not found!! ','error');}
        });
    document.body.insertBefore(brk, currentDiv);	
    document.body.insertBefore(cards, currentDiv);
    
    
      });
});

}

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
      Toast.show('Not found!! ','error')
    }
  });


})
function appform($this){
  var formid =$this.id;
  localStorage.setItem("FileNew", formid);
  window.location.replace("../html/appointData.html");


}
function cancel($this)
{
var slt = $this.id;
const usersCollection = database.collection('Bookings');
const ID = usersCollection.doc(slt).update({
    Status: "cancelled"


}).then(()=>{
  location.reload();
sendEmail();
})
}

function sendEmail() {
    
  Email.send({
  Host: "smtp.gmail.com",
  Username : "notdiscoveryemails2@gmail.com",
  Password : "SDgroup12",
  To :doctorData['Email'],
  From : "notdiscovery1@gmail.com",
  Subject : "Appointment Confirmation",
  Body : "Good Day " +doctorData['FirstName']+ "Your Appointment has been cancelled", 
  }).then(
  Toast.show('Email sent succesfully !! ','success')
  );
  }