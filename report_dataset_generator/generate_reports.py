import json
import random
from datetime import datetime, timedelta

# ----------------------------
# Data Pools for Report Fields
# ----------------------------

descriptions = [
    "Pothole on Main Street causing traffic delays.",
    "Broken streetlight at 5th Avenue Intersection.",
    "Water leakage in the basement of the community center.",
    "Graffiti vandalism on the east wall of the library.",
    "Damaged park bench in Riverside Park.",
    "Overflowing trash bins at Elm Street Plaza.",
    "Noise pollution from nearby construction site.",
    "Broken swing set in Greenwood Playground.",
    "Unattended litter around the central fountain.",
    "Fallen tree branch blocking the sidewalk on Pine Road."
]

locations = [
    "Main Street", "5th Avenue", "Elm Street", "Maple Avenue",
    "Oak Boulevard", "Pine Road", "Cedar Lane", "Birch Street",
    "Spruce Drive", "Willow Way"
]

priorities = ["LOW", "MIDDLE", "HIGH"]

categories = [
    "Maintenance", "Safety", "Public", "Utilities",
    "Environmental", "Community", "Infrastructure"
]

users = [
    "Alice Johnson", "Bob Smith", "Charlie Davis", "David Wilson",
    "Eve Martinez", "Frank Brown", "Grace Lee", "Hannah Taylor",
    "Ian Thomas", "Jane Moore"
]

# ----------------------------
# Function to Generate Random Timestamp
# ----------------------------

def generate_random_datetime():
    """
    Generates a random datetime within the past year.
    """
    now = datetime.now()
    days_back = random.randint(0, 365)
    seconds_back = random.randint(0, 86400)  # Number of seconds in a day
    random_datetime = now - timedelta(days=days_back, seconds=seconds_back)
    return random_datetime.strftime("%Y-%m-%dT%H:%M:%S")

# ----------------------------
# Function to Generate a Single Report Entry
# ----------------------------

def generate_report(report_id):
    """
    Generates a single report entry with randomized data.
    """
    description = random.choice(descriptions)
    location = random.choice(locations)
    priority = random.choice(priorities)
    category = random.choice(categories)
    user = random.choice(users)
    localDateTime = generate_random_datetime()
    likes = random.randint(0, 500)  # Assuming likes range from 0 to 500

    report = {
        "reportId": report_id,
        "description": description,
        "location": location,
        "priority": priority,
        "category": category,
        "user": user,
        "localDateTime": localDateTime,
        "likes": likes
    }

    return report

# ----------------------------
# Generate the Dataset
# ----------------------------

def generate_dataset(total_reports=2500):
    """
    Generates a dataset containing a specified number of report entries.
    """
    dataset = []
    for i in range(1, total_reports + 1):
        report = generate_report(i)
        dataset.append(report)
    return dataset

# ----------------------------
# Save Dataset to JSON File
# ----------------------------

def save_to_json(dataset, filename='reports_dataset.json'):
    """
    Saves the dataset to a JSON file.
    """
    with open(filename, 'w') as f:
        json.dump(dataset, f, indent=4)
    print(f"Dataset successfully saved to {filename}")

# ----------------------------
# Main Execution
# ----------------------------

if __name__ == "__main__":
    dataset = generate_dataset()
    save_to_json(dataset)
