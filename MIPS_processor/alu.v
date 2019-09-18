module alu(in_data0, in_data1, ctrl, zero, out_data);

	input		[31:0]	in_data0, in_data1;
	input		[3:0]	ctrl;
	output reg		zero; 
	output reg	[31:0]	out_data;
	
	reg	[31:0]	temp_data;
	reg 	[3:0]	ctrl;
	
	always @(1) begin
		
		// add
		if(ctrl == 3'b0010) begin
			out_data <= in_data0 + in_data1;
			zero <= 0;
		end
		
		// sub
		else if(ctrl ==	4'b0110) begin  //sub
			out_data <= in_data0 - in_data1;
			if(out_data == 1'b0) 
				zero <= 1;
			else 		
				zero <= 0;
		end

		// slt
		else if(ctrl ==	4'b0111) begin  //slt
			temp_data <= in_data0 - in_data1;
			out_data <= {31'b0, temp_data[31]};
			zero <= 0;
		end
		
		// and
		else if(ctrl ==	4'b0001) begin//and
			out_data <= in_data0 & in_data1;
			zero <= 0;
		end
		
		// or
		else if(ctrl ==	4'b0011) begin//or
			out_data <= in_data0 | in_data1;
			zero <= 0;
		end
		
		// nop
		else begin
			out_data <= 0;
			zero <= 0;
		end
	end
endmodule				
