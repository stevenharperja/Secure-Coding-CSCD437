for fname in *.png
do
cutname=$(basename $fname .png)
convert $fname -auto-orient "harpersTHM${cutname}.pdf"
done
