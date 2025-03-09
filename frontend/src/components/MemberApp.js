import React, { useState, useEffect } from "react";

const API_URL = "http://localhost:8083/members";

export default function MemberApp() {
	const [members, setMembers] = useState([]);
	const [form, setForm] = useState({ name: "", email: "", phone: "" });
	const [searchId, setSearchId] = useState("");
	const [searchedMember, setSearchedMember] = useState(null);

	// Fetch members on component mount
	useEffect(() => {
		fetch(API_URL)
			.then((res) => res.json())
			.then((data) => setMembers(data))
			.catch((err) => console.error("Error fetching members:", err));
	}, []);

	// Handle input changes without re-rendering the entire component
	const handleInputChange = (e) => {
		setForm((prevForm) => ({
			...prevForm,
			[e.target.name]: e.target.value,
		}));
	};

	// Add a new member
	const addMember = async () => {
		try {
			const res = await fetch(API_URL, {
				method: "POST",
				headers: { "Content-Type": "application/json" },
				body: JSON.stringify(form),
			});

			if (res.ok) {
				const newMember = await res.json();
				setMembers((prevMembers) => [...prevMembers, newMember]);
				setForm({ name: "", email: "", phone: "" });
			}
		} catch (error) {
			console.error("Error adding member:", error);
		}
	};

	// Search for a member by ID
	const searchMember = async () => {
		try {
			const res = await fetch(`${API_URL}/${searchId}`);
			if (res.ok) {
				setSearchedMember(await res.json());
			} else {
				setSearchedMember(null);
				alert("Member not found");
			}
		} catch (error) {
			console.error("Error searching member:", error);
		}
	};

	return (
		<div style={{ padding: "20px", maxWidth: "600px", margin: "auto" }}>
			{/* Add Member Form */}
			<div style={{ border: "1px solid #ccc", padding: "10px", marginBottom: "20px" }}>
				<h2>Add Member</h2>
				<input
					type="text"
					name="name"
					placeholder="Name"
					value={form.name}
					onChange={handleInputChange}
					style={{ display: "block", marginBottom: "10px", width: "100%", padding: "5px" }}
				/>
				<input
					type="email"
					name="email"
					placeholder="Email"
					value={form.email}
					onChange={handleInputChange}
					style={{ display: "block", marginBottom: "10px", width: "100%", padding: "5px" }}
				/>
				<input
					type="tel"
					name="phone"
					placeholder="Phone"
					value={form.phone}
					onChange={handleInputChange}
					style={{ display: "block", marginBottom: "10px", width: "100%", padding: "5px" }}
				/>
				<button onClick={addMember} style={{ padding: "8px", width: "100%" }}>Add</button>
			</div>

			{/* Search Member By ID */}
			<div style={{ border: "1px solid #ccc", padding: "10px", marginBottom: "20px" }}>
				<h2>Search Member by ID</h2>
				<input
					type="text"
					placeholder="Enter ID"
					value={searchId}
					onChange={(e) => setSearchId(e.target.value)}
					style={{ display: "block", marginBottom: "10px", width: "100%", padding: "5px" }}
				/>
				<button onClick={searchMember} style={{ padding: "8px", width: "100%" }}>Search</button>
				{searchedMember && (
					<div style={{ marginTop: "10px", padding: "10px", border: "1px solid #ddd" }}>
						<p><strong>Name:</strong> {searchedMember.name}</p>
						<p><strong>Email:</strong> {searchedMember.email}</p>
						<p><strong>Phone:</strong> {searchedMember.phone}</p>
					</div>
				)}
			</div>

			{/* Member List */}
			<div style={{ border: "1px solid #ccc", padding: "10px" }}>
				<h2>Member List</h2>
				<table style={{ width: "100%", borderCollapse: "collapse" }}>
					<thead>
					<tr style={{ borderBottom: "1px solid #ddd" }}>
						<th style={{ textAlign: "left", padding: "5px" }}>ID</th>
						<th style={{ textAlign: "left", padding: "5px" }}>Name</th>
						<th style={{ textAlign: "left", padding: "5px" }}>Email</th>
						<th style={{ textAlign: "left", padding: "5px" }}>Phone</th>
					</tr>
					</thead>
					<tbody>
					{members.map((member) => (
						<tr key={member.id} style={{ borderBottom: "1px solid #ddd" }}>
							<td style={{ padding: "5px" }}>{member.id}</td>
							<td style={{ padding: "5px" }}>{member.name}</td>
							<td style={{ padding: "5px" }}>{member.email}</td>
							<td style={{ padding: "5px" }}>{member.phone}</td>
						</tr>
					))}
					</tbody>
				</table>
			</div>
		</div>
	);
}
