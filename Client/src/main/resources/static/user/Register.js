function registerOwner() {
    const fullname = document.querySelector('input[name="fullname"]').value.trim();
    const email = document.querySelector('input[name="Email"]').value.trim();
    const password = document.querySelector('input[name="Password"]').value;
    const confirmPassword = document.querySelector('input[name="ConfirmPassword"]').value;


    if (!fullname || !email || !password || !confirmPassword) {
        Swal.fire("Error", "All fields are required.", "error");
        return;
    }

    if (password !== confirmPassword) {
        Swal.fire("Error", "Passwords do not match.", "error");
        return;
    }

    const formData = {
        fullname,
        Email: email,
        Password: password,
        ConfirmPassword: confirmPassword
    };

    fetch('http://localhost:8686/registerOwner', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(formData)
    })
        .then(response => {
            if (response.ok) {
                Swal.fire("Success", "Registration successful.", "success");
            } else {
                Swal.fire("Error", "Could not register. Please try again.", "error");
            }
        })
        .catch(error => {
            console.log("Error:", error);
            Swal.fire("Error", "An unexpected error occurred. Please try again.", "error");
        });
}
