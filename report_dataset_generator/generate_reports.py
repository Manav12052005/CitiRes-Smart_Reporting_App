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
    # Add more descriptions as needed
]

locations = [
    "Main Street", "5th Avenue", "Elm Street", "Maple Avenue",
    "Oak Boulevard", "Pine Road", "Cedar Lane", "Birch Street",
    "Spruce Drive", "Willow Way"
    # Add more locations as needed
]

priorities = ["LOW", "MIDDLE", "HIGH"]

categories = [
    "Maintenance", "Safety", "Public", "Utilities",
    "Environmental", "Community", "Infrastructure"
    # Add more categories as needed
]

users = [
    "Alice Johnson", "Bob Smith", "Charlie Davis", "David Wilson",
    "Eve Martinez", "Frank Brown", "Grace Lee", "Hannah Taylor",
    "Ian Thomas", "Jane Moore"
    # Add more user names as needed
]

# ----------------------------
# Function to Generate Random Timestamp
# ----------------------------

def generate_incremental_datetime(base_datetime, days_increment=0, seconds_increment=0):
    """
    Generates a datetime incremented by a specified number of days and seconds.
    
    :param start_datetime: The starting datetime.
    :param days_increment: The number of days to increment.
    :param seconds_increment: The number of seconds to increment.
    :return: Incremented datetime as a formatted string.
    """
    return (base_datetime + timedelta(days=days_increment, seconds=seconds_increment)).strftime("%Y-%m-%dT%H:%M:%S")



# ----------------------------
# Function to Generate a Single Report Entry
# ----------------------------

def generate_report(report_id, report_date):
    """
    Generates a single report entry with randomized data and an incremented datetime.
    """
    description = random.choice(descriptions)
    location = random.choice(locations)
    priority = random.choice(priorities)
    category = random.choice(categories)
    user = random.choice(users)

    # Use the provided report_date instead of generating a new one
    localDateTime = report_date.strftime("%Y-%m-%dT%H:%M:%S")
    
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

def generate_dataset(total_reports=2500, start_date=None):
    """
    Generates a dataset containing a specified number of report entries,
    with dates incremented by 1 hour for each report starting from 2020.
    
    :param total_reports: The total number of reports to generate.
    :param start_date: The starting date for the first report (defaults to January 1st, 2020, 00:00).
    :return: A list of report dictionaries.
    """
    if start_date is None:
        # Start from January 1st, 2020, 00:00
        start_date = datetime(2020, 1, 1, 0, 0)
    
    dataset = []
    for i in range(1, total_reports + 1):
        report_date = start_date + timedelta(hours=i-1)  # Increment by 1 hour for each report
        report = generate_report(i, report_date)
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
