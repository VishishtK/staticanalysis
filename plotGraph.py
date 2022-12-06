import matplotlib.pyplot as plt
import pandas as pd
import os
import glob

# use glob to get all the csv files
# in the folder
path = os.getcwd()
csv_files = glob.glob(os.path.join(path, "*.csv"))
dfList = []
files = []

# loop over the list of csv files
for f in csv_files:

    # read the csv file
    df = pd.read_csv(f)
    files.append(f.split("/")[-1])
    dfList.append(df)

for df in dfList:
    print(df.loc[:, "Type"])
  
columns=['Files'] + files

# create data
df = pd.DataFrame([['A', 10, 20, 10], ['B', 20, 25, 15], ['C', 12, 15, 19],
                   ['D', 10, 29, 13]],
                  columns=columns)
# view data
print(df)
  
# plot grouped bar chart
df.plot(x='Files',
        kind='bar',
        stacked=False,
        title='Grouped Bar Graph with dataframe')

plt.show()
