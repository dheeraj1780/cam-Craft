function generateSegments() {
            var numSegments = document.getElementById('numSegments').value;
            var segmentsContainer = document.getElementById('segments');
            segmentsContainer.innerHTML = '';

            for (var i = 0; i < numSegments; i++) {
                var segmentDiv = document.createElement('div');
                segmentDiv.classList.add('segment');
                
                var segmentHeader = document.createElement('h3');
                segmentHeader.innerText = 'Segment ' + (i + 1);
                segmentDiv.appendChild(segmentHeader);

                var segmentTypeLabel = document.createElement('label');
                segmentTypeLabel.setAttribute('for', 'segmentType' + i);
                segmentTypeLabel.innerText = 'Segment Type:';
                segmentDiv.appendChild(segmentTypeLabel);

                var segmentTypeSelect = document.createElement('select');
                segmentTypeSelect.id = 'segmentType' + i;
                segmentTypeSelect.name = 'segmentType' + i;
                segmentTypeSelect.required = true;
                segmentTypeSelect.innerHTML = `
                    <option value="rise">Rise</option>
                    <option value="dwell">Dwell</option>
                    <option value="return">Return</option>
                `;
                segmentTypeSelect.onchange = function() {
                    var motionSelect = this.parentElement.querySelector('select[name^="segmentMotionType"]');
                    if (this.value === 'dwell') {
                        motionSelect.value = 'constant';
                        motionSelect.disabled = true;
                    } else {
                        motionSelect.disabled = false;
                    }
                };
                segmentDiv.appendChild(segmentTypeSelect);
                segmentDiv.appendChild(document.createElement('br'));
                segmentDiv.appendChild(document.createElement('br'));

                var angleLabel = document.createElement('label');
                angleLabel.setAttribute('for', 'segmentAngle' + i);
                angleLabel.innerText = 'Angle Duration:';
                segmentDiv.appendChild(angleLabel);

                var angleInput = document.createElement('input');
                angleInput.type = 'number';
                angleInput.id = 'segmentAngle' + i;
                angleInput.name = 'segmentAngle' + i;
                angleInput.step = '0.01';
                angleInput.required = true;
                segmentDiv.appendChild(angleInput);
                segmentDiv.appendChild(document.createElement('br'));
                segmentDiv.appendChild(document.createElement('br'));

                var motionLabel = document.createElement('label');
                motionLabel.setAttribute('for', 'segmentMotionType' + i);
                motionLabel.innerText = 'Motion Type:';
                segmentDiv.appendChild(motionLabel);

                var motionSelect = document.createElement('select');
                motionSelect.id = 'segmentMotionType' + i;
                motionSelect.name = 'segmentMotionType' + i;
                motionSelect.required = true;
                motionSelect.innerHTML = `
                    <option value="uniform">Uniform</option>
                    <option value="harmonic">Harmonic</option>
                    <option value="parabolic">Parabolic</option>
                    <option value="constant">Constant</option>
                `;
                segmentDiv.appendChild(motionSelect);
                segmentDiv.appendChild(document.createElement('br'));
                segmentDiv.appendChild(document.createElement('br'));
                
                segmentsContainer.appendChild(segmentDiv);
            }
        }

function toggleFollowerRadius() {
            var followerType = document.getElementById('followerType').value;
            var followerRadius = document.getElementById('followerRadius');

            if (followerType === 'roller' || followerType === 'spherical-faced') {
                followerRadius.disabled = false;
            } else {
                followerRadius.disabled = true;
                followerRadius.value = ''; // Clear the value when disabled
            }
        }