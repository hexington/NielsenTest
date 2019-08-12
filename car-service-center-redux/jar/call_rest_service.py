'''
Created on Aug 7, 2019

@author: Hexington
'''

import requests

class ApiError(Exception):
    """An API Error Exception"""
    def __init__(self, status):
        self.status = status

    def __str__(self):
        return "APIError: status={}".format(self.status)
    
def get_appointment():
    while True:
        try:
            appt_id = input(f'\nEnter an appointment id: ')
            if appt_id == 'q':
                return
            else:
                appt_id = int(appt_id)
        except ValueError:
            print('appointment id must be a number')
        else:
            break
    resp = requests.get(f'http://localhost:8080/getappointment/{appt_id}')
    if resp.status_code != 200:
        # This means something went wrong.
        if resp.status_code == 404:
            print('Status code = 404: Resource Not Found')
        else:
            #raise ApiError('GET /getappointment/ {}'.format(resp.status_code))
            print(f'Error occurred. Status Code: {resp.status_code}')
            print(resp.json())
    else:
        print(resp.json())
    
def get_range():
    print('Date range format is: MM-dd-yyyy,MM-dd-yyyy')
    
    while True:
        date_range = input(f'\nEnter from date to date: ')
        if date_range == 'q':
            return
        elif date_range.isspace():
            continue
        else:
            try:
                date_range_arr = date_range.split(',')
                get_range_url = f'http://localhost:8080/getrange/from/{date_range_arr[0]}/to/{date_range_arr[1]}'
            except IndexError:
                print('Date range format is: MM-dd-yyyy,MM-dd-yyyy')
                continue
            else:
                break
    
    resp = requests.get(get_range_url)
    if resp.status_code != 200:
        # This means something went wrong.
        raise ApiError('GET /getrange/ {}'.format(resp.status_code))
    
    if len(resp.json()) == 0:
        print("There are no appointments within this range.")
    else:
        for appointments in resp.json():
            print(appointments)
        
def create_appointment():
    print('Appointment format is: FirstName,LastName,Car,yyyy-MM-dd,price,ONTIME|DELAYED')
    while True:
        appointment = input(f'\nEnter appointment: ')
        if appointment == 'q':
            return
        elif appointment.isspace():
            continue
        else:
            try:
                appointment_arr = appointment.split(',')
                appt_json = {'firstName': appointment_arr[0], 
                     'lastName': appointment_arr[1], 
                     'car': appointment_arr[2], 
                     'dateOfAppt': appointment_arr[3], 
                     'price': appointment_arr[4], 
                     'statusOfAppt': appointment_arr[5]}
            except IndexError:
                print('Appointment format is: FirstName,LastName,Car,yyyy-MM-dd,price,ONTIME|DELAYED')
                continue
            else:
                break
    
    resp = requests.post(f'http://localhost:8080/createappointment', json=appt_json)
    if resp.status_code != 201:
        #raise ApiError('POST /createappointment/ {}'.format(resp.status_code))
        print(f'Error occurred. Status Code: {resp.status_code}')
        print(resp.json())
    else:
        print('Created task. ID: {}'.format(resp.json()["appointmentId"]))
    
def update_appointment():
    print('Update format is: AppointmentId,FirstName,LastName,Car,yyyy-MM-dd,price,ONTIME|DELAYED')
    while True:
        appointment = input(f'\nEnter appointment update: ')
        if appointment == 'q':
            return
        elif appointment.isspace():
            continue
        else:
            try:
                appointment_arr = appointment.split(',')
                appt_json = {'AppointmentId': appointment_arr[0], 
                     'firstName': appointment_arr[1], 
                     'lastName': appointment_arr[2], 
                     'car': appointment_arr[3], 
                     'dateOfAppt': appointment_arr[4], 
                     'price': appointment_arr[5], 
                     'statusOfAppt': appointment_arr[6]}
            except IndexError:
                print('Update format is: AppointmentId,FirstName,LastName,Car,yyyy-MM-dd,price,ONTIME|DELAYED')
                continue
            else:
                break
    
    resp = requests.post(f'http://localhost:8080/updateappointment/{appointment_arr[0]}', json=appt_json)
    if resp.status_code != 200:
        #raise ApiError('POST /updateappointment/ {}'.format(resp.status_code))
        if resp.status_code == 404:
            print('Status code = 404: Resource Not Found')
        else:
            print(f'Error occurred. Status Code: {resp.status_code}')
            print(resp.json())
    else:
        print('Updated task:')
        print(resp.json())

def delete_appointment():
    while True:
        try:
            appt_id = input(f'\nEnter an appointment id to delete: ')
            if appt_id == 'q':
                return
            else:
                appt_id = int(appt_id)
        except ValueError:
            print('appointment id must be a number')
        else:
            break
    
    resp = requests.delete(f'http://localhost:8080/deleteappointment/{appt_id}')
    if resp.status_code != 200:
        # This means something went wrong.
        if resp.status_code == 404:
            print('Status code = 404: Resource Not Found')
        else:
            #raise ApiError('GET /getappointment/ {}'.format(resp.status_code))
            print(f'Error occurred. Status Code: {resp.status_code}')
    else:
        print(f'{appt_id} has been deleted.')

if __name__ == "__main__":
    while True:
        print('\nMenu: c = create, g = get, u = update, d = delete, r = range, q = quit')
        acceptable = ('c','g','r','q','u','d')
        selection = ''
        while selection not in acceptable:
            selection = input(f'Enter Selection: ')
            
        if selection == 'g':
            get_appointment()
        elif selection == 'c':
            create_appointment()
        elif selection == 'r':
            get_range()
        elif selection == 'u':
            update_appointment()
        elif selection == 'd':
            delete_appointment()
        elif selection == 'q':
            break
            
    
