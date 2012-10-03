require 'csv'

def the_function	
  python = 0
  ruby = 0
  CSV.foreach("Test.csv") do |row|
	if (row[4]==" Ruby")			
		ruby = ruby + row[2].to_i
	else
		if (row[4]==" Python")
			python = python + row[2].to_i
		end
	end
end
  print 'Python: ', python, "\n"
  print 'Ruby: ', ruby, "\n" 
end

the_function